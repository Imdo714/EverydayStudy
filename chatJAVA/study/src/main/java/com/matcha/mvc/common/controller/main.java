package com.matcha.mvc.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main {

	@RequestMapping("/main.te")
	public String maing(Model model){
		
		return "redirect:/";
	}
}
