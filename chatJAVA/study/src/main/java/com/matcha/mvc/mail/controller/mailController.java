package com.matcha.mvc.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mailController {

	@RequestMapping("/mail.ml")
	public String mail(Model model){
		
		return "mail/mail";
	}
	
	@RequestMapping("/insertM.ml")
	public String insertM(Model model){
		
		return "mail/insertMail";
	}
}
