package com.matcha.mvc.designer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class designerController {

	@RequestMapping("/designer.di")
	public String des(Model model){
		
		return "designer/designer";
	}
}
