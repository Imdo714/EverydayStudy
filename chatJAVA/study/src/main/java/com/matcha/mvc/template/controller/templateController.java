package com.matcha.mvc.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.template.service.templateService;

@Controller
public class templateController {

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
	

}
