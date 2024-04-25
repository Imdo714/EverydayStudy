package com.matcha.mvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.member.service.memberService;


@Controller
public class memberController {
	
	@Autowired
	memberService memberService;
	
//	마이 페이지에서 내 템플릿 보기
	@RequestMapping("/myTemplate.me")
	public String mytemAll(Model model){
		
		return "member/myTemplateAll";
	}
	
//	마이 페이지에서 내 댓글 보기
	@RequestMapping("/myComment.me")
	public String mycomAll(Model model){
		
		return "member/myCommentAll";
	}

}
