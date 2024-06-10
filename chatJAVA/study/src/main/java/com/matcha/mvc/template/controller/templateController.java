package com.matcha.mvc.template.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.matcha.mvc.common.page.Pagenation;
import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.member.vo.Member;
import com.matcha.mvc.template.service.templateService;
import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;
import com.matcha.mvc.template.vo.TemplateReply;

@Controller
public class templateController {
	
	Gson gson = new Gson();

	@Autowired
	templateService templateService;
	
	
	@RequestMapping("/insertT.te")
	public String inT(Model model){
		
		return "template/insertTemplate";
	}
	
	@RequestMapping("/detailT.te")
	public String deT(Model model){
		
		return "template/detailTemplate";
	}

	public String saveFile(MultipartFile upfile, HttpSession session, String filepath) {
		//파일명 수정 후 서버 업로드 시키기(기존파일명 -> 202311091027+5자리랜덤숫자+파일형식)
		//년월일시분초 + 랜덤숫자 5개 + 확장자 
		
		//원래파일명
		String originName = upfile.getOriginalFilename();
		
		//시간정보(년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(0));
		
		//랜덤숫자 5자리
		int ranNum = (int)(Math.random()* 90000) + 10000;// 10000부터 99999까지
		
		//확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		//변경된 이름
		String changeName = currentTime + ranNum + ext;
		
		//첨부파일 저장할 폴더의 물리적인 경로
		String savePath = session.getServletContext().getRealPath(filepath);
		
		try {
			upfile.transferTo(new File(savePath + changeName));//업로드 파일의 정보를 변경해서넣어줘 
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		return changeName;
	}
	
//  -------------------------------템플릿 작성 메서드------------------------------------
	@ResponseBody  
	@RequestMapping(value="/insertTem.te", produces="application/json; charset=UTF-8")
	public String templateForm(Template t, HttpSession session, ArrayList<MultipartFile> upfile, @RequestParam(value="name", required=false) List<String> name)  {
				
	    Member m = (Member) session.getAttribute("loginUser");
	    int summer = 0; int fileUploadResult = 0; //int temp = 0;
//	    
		int temp = templateService.templateInsert(t, m.getUserNo()); // 템플릿 작성 
	    
		for(MultipartFile mf : upfile) {
			//전달된 파일이 있을 경우 => 파일명 수정 후 서버 업로드 => 원본명, 서버업로드된 경로로 DB에 담기(파일이 있을때만)
			if(mf != null && !mf.isEmpty() && !"".equals(mf.getOriginalFilename().trim())) {
				TemplateImg ti = new TemplateImg();
				String changeName = saveFile(mf, session, "resources/img/templateImgFile/titleTemplate/");
				
				ti.setTemplateImgUrl("resources/img/templateImgFile/titleTemplate/");
				ti.setTemplateOrginName(mf.getOriginalFilename());
				ti.setTemplateChangName("resources/img/templateImgFile/titleTemplate/" + changeName);
				
				fileUploadResult = templateService.templateTitleImg(ti); // 썸네일 기입
			}
		}
		
//		String[] fileNames = name.split(",");
		if (name != null && !name.isEmpty()) {
		    for (String fileName : name) {
		        if(fileName != null) {
		        	TemplateImg sti = new TemplateImg();
		        	
		        	sti.setTemplateImgUrl("resources/img/templateImgFile/titleTemplate/");
					sti.setTemplateOrginName("썸머노트");
					sti.setTemplateChangName(fileName);
					
					summer = templateService.summerImgName(sti);
		        }
		    }  
		}
	    
	    int result = temp + fileUploadResult;

		return new Gson().toJson(result);
	}
	
	
	
	
//  서머노트 작성시 이미지파일 올렸을때 내 실제 경로 폴더에도 올려주는 메서드
	@ResponseBody
	@RequestMapping(value="/insertTemplateImg.te", produces="application/json; charset=UTF-8")
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session )  {
		
		String cName = saveFile(multipartFile, session, "resources/img/templateImgFile/insertTemplate/");
		String changeName = "resources/img/templateImgFile/insertTemplate/" + cName;
		
		return new Gson().toJson(changeName);
	}
	
// 서머노트 작성시 이미지파일 삭제했을때 내 실제 경로 폴더에도 삭제하는 메서드 
	@ResponseBody
	@RequestMapping(value="/deleteTemplateImage.te", produces = "application/json; charset=utf8")
	public String deleteSummernoteImageFile(@RequestParam("file") String file, HttpSession session )  {
	
		new File(session.getServletContext().getRealPath("resources/img/templateImgFile/insertTemplate/"+file)).delete();
		
		return "yes!";
	}
	
// ----------------------------- 템플릿 리스트 보여주기 -----------------------------
	@RequestMapping("/template.te")
	public ModelAndView tem(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv){
		
		PageInfo pi = Pagenation.getPageInfo(templateService.selectListCount(), currentPage, 5, 9);
		
		ArrayList<Template> list = templateService.selectTemplateList(pi);
		
		mv.addObject("pi", pi).addObject("list", list).setViewName("template/template");
		
		return mv;
	}
	
//  ----------------------------- 디테일 템플릿 자세히보기 메서드 -----------------------------
	@RequestMapping("/detailTemplate.te")
	public ModelAndView templateDetail(@RequestParam(value="tpage", defaultValue="1") int currentPage, int tno, ModelAndView mv){
		
		ArrayList<Template> list = templateService.detailTemplate(tno);
		
		PageInfo pi = Pagenation.getPageInfo(templateService.selectReplyCount(tno), currentPage, 5, 5);
		
		ArrayList<TemplateReply> ReplyList = templateService.detailReplyTemplate(pi, tno);
		
		mv.addObject("list", list).addObject("ReplyList", ReplyList).addObject("pi", pi).addObject("tno", tno).setViewName("template/detailTemplate");
		
		return mv;
	}

//  ----------------------------- 템플릿 서머노트 업데이트 메서드 -------------------------
	@ResponseBody
	@RequestMapping(value="/updateTemplate.te", produces="application/json; charset=UTF-8")
	public String updateSummernote(Template t, ModelAndView mv)  {
		
		return new Gson().toJson(templateService.updateTemplate(t) > 0 ? "success" : "fail");
	}

// --------------------------------- 공통된 작업을 수행하는 메서드 ---------------------------------
	private ModelAndView getReplyModelAndView(int tno, HttpSession session, int currentPage) {
		
	    PageInfo pi = Pagenation.getPageInfo(templateService.selectReplyCount(tno), currentPage, 5, 5); // 페이징 처리
	    ArrayList<TemplateReply> ReplyList = templateService.detailReplyTemplate(pi, tno); // 댓글 정보
	    
	    // 삼항 연산자 세션이 없으면 0반환 있으면 회원 번호 반환
	    int userNo = session.getAttribute("loginUser") == null ? 0 : ((Member) session.getAttribute("loginUser")).getUserNo();
	    
	    ModelAndView mv = new ModelAndView(); // 객체 생성 조회된 댓글 정보와 페이징 정보 그리고 사용자 번호를 ModelAndView 객체에 담아 반환
	    mv.addObject("ReplyList", ReplyList).addObject("tno", tno).addObject("userNo", userNo).addObject("pi", pi);
	    
	    return mv;
	}

// --------------------------------- Ajax 댓글 작성하는 메서드 ---------------------------------
	@ResponseBody
	@RequestMapping(value="/repltInsert.te", produces="application/json; charset=UTF-8")
	public String Reply(TemplateReply r, int tno, ModelAndView mv, HttpSession session, @RequestParam(value="cpage", defaultValue="1") int currentPage)  {
		
	    Member m = (Member) session.getAttribute("loginUser");
	    int res = templateService.replyInsert(r, m.getUserNo()); // 댓글 삽입
	    
	    ModelAndView newMv = getReplyModelAndView(tno, session, currentPage);  // 메서드 호출 매개변수로는 댓글번호, 세션, currentPage를 전달 
	    
	    return new Gson().toJson(newMv); // newMv로 리턴 받은 조회된 댓글 정보와 페이징 정보 그리고 사용자 번호를 반환
	}

// --------------------------------- Ajax onload로 디테일뷰 댓글, 페이징 바 그려주는 메서드 ---------------------------------
	@ResponseBody
	@RequestMapping(value="/onloadReply.te", produces="application/json; charset=UTF-8")
	public String Replyload(int tno, ModelAndView mv, HttpSession session, @RequestParam(value="tpage", defaultValue="1") int currentPage)  {
		
	    ModelAndView newMv = getReplyModelAndView(tno, session, currentPage);
	    return new Gson().toJson(newMv);
	}

//  --------------------------------- Ajax 댓글 삭제해주는 메서드 -------------------------------------
	@ResponseBody
	@RequestMapping(value="/replyDelte.te", produces="application/json; charset=UTF-8")
	public String replyDelte(TemplateReply tr, ModelAndView mv, HttpSession session, @RequestParam(value="tpage", defaultValue="1") int currentPage)  {
		
	    int res = templateService.replyDelt(tr.getTemplateReplyNo()); // 댓글 삭제
	    
	    ModelAndView newMv = getReplyModelAndView(tr.getTemplateNo(), session, currentPage);
	    return new Gson().toJson(newMv);
	}
	
//  --------------------------------- Ajax 댓글 수정해주는 메서드 -------------------------------------
	@ResponseBody
	@RequestMapping(value="/replyUpdate.te", produces="application/json; charset=UTF-8")
	public String replyUpdate(TemplateReply tr, ModelAndView mv, HttpSession session, @RequestParam(value="tpage", defaultValue="1") int currentPage)  {
		
	    int upReply = templateService.replyUpdate(tr);
	    
	    ModelAndView newMv = getReplyModelAndView(tr.getTemplateNo(), session, currentPage);
	    return new Gson().toJson(newMv);
	}

//	마이 페이지에서 내 템플릿 보기
	@RequestMapping("/myTemplate.te")
	public ModelAndView mytemAll(HttpSession session, ModelAndView mv){
		
		Member m = (Member) session.getAttribute("loginUser");
		ArrayList<Template> list = templateService.selectUserTemplate(m.getUserNo());
		
		mv.addObject("list", list).setViewName("member/myTemplateAll");
		
		return mv;
	}
	
//  템플릿 관련 모두 삭제
	@ResponseBody
	@RequestMapping(value="/delteTemplate.te", produces="application/json; charset=UTF-8")
	public String replyUpdate(int templateNo, ModelAndView mv, HttpSession session)  {
		
		ArrayList<TemplateImg> list = templateService.TemplateImgAll(templateNo);
		
		for(TemplateImg templateImg  : list) {
			String changName = templateImg.getTemplateChangName();
			new File(session.getServletContext().getRealPath(changName)).delete(); // 폴더에있는 이미지 삭제
		}
		
		int tem = templateService.templateAllDelte(templateNo); // 템플릿 관련 모두 삭제
		
	    return new Gson().toJson(tem);
	}
}
