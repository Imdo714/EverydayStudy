package com.matcha.mvc.mail.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.mail.dao.mailDao;
import com.matcha.mvc.mail.vo.Mail;

@Service
public class mailServiceImpl implements mailService{
	
	@Autowired
	private mailDao mailDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int sendMail(Mail m, int userNo) {
		return mailDao.sendMail(sqlSession, m, userNo);
	} 
	
}
