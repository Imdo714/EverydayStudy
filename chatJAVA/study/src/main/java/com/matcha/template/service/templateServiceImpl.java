package com.matcha.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.template.dao.templateDao;

@Service
public class templateServiceImpl implements templateService{

	@Autowired
	private templateDao templateDao;
	
	
}
