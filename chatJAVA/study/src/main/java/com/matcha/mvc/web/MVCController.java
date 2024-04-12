package com.matcha.mvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.service.MVCService;

@Controller
public class MVCController {

	@Autowired
	MVCService mvcservice;
	
	@RequestMapping("/index.do")
	public String sayHello(Model model) throws Exception {
		model.addAttribute("Hello", mvcservice.sayHello());
		
		return "board/test";
	}
	
	@RequestMapping("/review.te")
	public String tem(Model model){
		
		return "review/review";
	}
}
