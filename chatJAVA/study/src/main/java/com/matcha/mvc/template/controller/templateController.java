package com.matcha.mvc.template.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
	
	
//  템플릿 작성
	@RequestMapping("/insertTem.te")
	public String templateInsert(Template t, HttpSession session, ArrayList<MultipartFile> upfile){
		
		Member m = (Member) session.getAttribute("loginUser");
		int userNo = m.getUserNo();
		
		int result = 0;
		int fileUploadResult  = 0;
		
		result = templateService.templateInsert(t, userNo);
		
		
		
		for(MultipartFile mf : upfile) {
			//전달된 파일이 있을 경우 => 파일명 수정 후 서버 업로드 => 원본명, 서버업로드된 경로로 DB에 담기(파일이 있을때만)
			if(!mf.getOriginalFilename().equals("")) {
				TemplateImg ti = new TemplateImg();
				String changeName = saveFile(mf, session, "resources/img/templateImgFile/titleTemplate/");
				
				ti.setTemplateImgUrl("resources/img/templateImgFile/titleTemplate/");
				ti.setTemplateOrginName(mf.getOriginalFilename());;
				ti.setTemplateChangName("resources/img/templateImgFile/titleTemplate/" + changeName);
				
				fileUploadResult = templateService.templateTitleImg(ti);
			}
		}
		
		if(result > 0 && fileUploadResult > 0) {
			session.setAttribute("alertMsg", "게시글 작성 성공");
		} else {
			session.setAttribute("alertMsg", "게시글 작성 Fuck");
		}
		
		return "redirect:/";
	} 
	
	
//  서머노트 작성시 이미지파일 올렸을때 내 실제 경로 폴더에도 올려주는 메서드
	@ResponseBody
	@RequestMapping(value="/insertTemplateImg.te", produces="application/json; charset=UTF-8")
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session )  {
		
		System.out.println(multipartFile);

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
	
// 템플릿 리스트 보여주기
	@RequestMapping("/template.te")
	public ModelAndView tem(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv){
		
		PageInfo pi = Pagenation.getPageInfo(templateService.selectListCount(), currentPage, 5, 9);
		
		ArrayList<Template> list = templateService.selectTemplateList(pi);
		
		mv.addObject("pi", pi)
		  .addObject("list", list)
		  .setViewName("template/template");
		
		return mv;
	}
	

}
