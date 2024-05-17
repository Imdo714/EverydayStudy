package com.matcha.mvc.review.controller;

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
import com.matcha.mvc.review.service.reviewService;
import com.matcha.mvc.review.vo.Review;
import com.matcha.mvc.review.vo.ReviewImg;
import com.matcha.mvc.template.vo.Template;

@Controller
public class reviewController {
	
	@Autowired
	reviewService reviewService;
	

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
	
	@RequestMapping("/reviewinsert.re")
	public String insertReview(Review r, MultipartFile file, HttpSession session){

		int rev = reviewService.insertReview(r);
		
		// 파일이 실제로 존재하고 비어 있지 않으며 파일 이름이 비어 있지 않음을 확인 하며 NullPointerException을 피할 수 있는 안전한 방법입니다.
		if (file != null && !file.isEmpty() && !"".equals(file.getOriginalFilename().trim())) {
			ReviewImg ri = new ReviewImg();
			String changeName = saveFile(file, session, "resources/img/reviewFile/reviewImg/");
				
			ri.setReviewImgUrl("resources/img/reviewFile/reviewImg/");
			ri.setReviewOrginName(file.getOriginalFilename());
			ri.setReviewChangName("resources/img/reviewFile/reviewImg/" + changeName);
			
			int reImg = reviewService.reviewImgInsert(ri);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/review.re")
	public ModelAndView tem(@RequestParam(value="rpage", defaultValue="1") int currentPage, ModelAndView mv){
		
		PageInfo pi = Pagenation.getPageInfo(reviewService.selectListCount(), currentPage, 5, 9);
		
		ArrayList<Review> list = reviewService.selectReviewList(pi);
		
		mv.addObject("pi", pi).addObject("list", list).setViewName("review/review");
		
		return mv;
	}
	
//  리뷰 삭제시 리뷰 관련된 거 다 삭제하는 메서드
	@ResponseBody
	@RequestMapping(value="/deleteReview.re", produces="application/json; charset=UTF-8")
	public String uploadSummernoteImageFile(int reviewNo, HttpSession session )  {
		
		ArrayList<ReviewImg> list = reviewService.selectReviewImgUrl(reviewNo);
		
		String reviewChangName = "";
		String reviewImgUrl = "";
		
		for (ReviewImg reviewImg : list) {
			reviewChangName = reviewImg.getReviewChangName();
			reviewImgUrl = reviewImg.getReviewImgUrl();
        }
		
		if(list != null) {
			int delImg = reviewService.deletReviewImg(reviewNo); // DB 리뷰 이미지 삭제
			new File(session.getServletContext().getRealPath(reviewChangName)).delete(); // 폴더에있는 사진 삭제
		}
		
		int del = reviewService.deletReview(reviewNo); // DB에서 리뷰 삭제
		
		return new Gson().toJson(del);
	}

	
	
}
