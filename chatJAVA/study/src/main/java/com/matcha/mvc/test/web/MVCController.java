package com.matcha.mvc.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.test.service.MVCService;

@Controller
public class MVCController {

	@Autowired
	MVCService mvcservice;
	
	@RequestMapping("/index.do")
	public String sayHello(Model model) throws Exception {
		model.addAttribute("Hello", mvcservice.sayHello());
		
		return "board/test";
	}
	

}
