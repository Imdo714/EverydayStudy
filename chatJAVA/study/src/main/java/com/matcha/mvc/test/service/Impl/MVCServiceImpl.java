package com.matcha.mvc.test.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.test.dao.MVCDao;
import com.matcha.mvc.test.service.MVCService;

@Service
public class MVCServiceImpl implements MVCService{

	@Autowired
	private MVCDao mvcdao;
	
	@Override
	public String sayHello() {
		System.out.println(mvcdao.sayHello()+"================================");
		return "안녕하시렵니까";
	}
}
