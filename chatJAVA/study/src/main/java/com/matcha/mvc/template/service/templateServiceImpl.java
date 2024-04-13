package com.matcha.mvc.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.template.dao.templateDao;

@Service
public class templateServiceImpl implements templateService{

	@Autowired
	private templateDao templateDao;
	
	
}
