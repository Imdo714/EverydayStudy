package com.matcha.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.template.service.templateService;

@Controller
public class templateController {

	@Autowired
	templateService templateService;
	
	@RequestMapping("/template.te")
	public String tem(Model model){
		
		return "template/template";
	}
	
	
}
