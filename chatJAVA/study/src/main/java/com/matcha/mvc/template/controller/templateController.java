package com.matcha.mvc.template.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.matcha.mvc.template.service.templateService;
import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;

@Controller
public class templateController {
	
	Gson gson = new Gson();

	@Autowired
	templateService templateService;
	
	@RequestMapping("/template.te")
	public String tem(Model model){
		
		return "template/template";
	}
	
	@RequestMapping("/insertT.te")
	public String inT(Model model){
		
		return "template/insertTemplate";
	}
	
	@RequestMapping("/detailT.te")
	public String deT(Model model){
		
		return "template/detailTemplate";
	}
	
//  템플릿 작성
	@RequestMapping("/insertTem.te")
	public String templateInsert(Template t){
		
		System.out.println(t);
		
		return "template/insertTemplate";
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
	


	

}
