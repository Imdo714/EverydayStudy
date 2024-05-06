package com.matcha.mvc.template.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.template.dao.templateDao;
import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;

@Service
public class templateServiceImpl implements templateService{

	@Autowired
	private templateDao templateDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession; 

	@Override
	public int templateInsert(Template t, int userNo) {
		return templateDao.templateInsert(sqlSession, t, userNo);
	}

	@Override
	public int templateTitleImg(TemplateImg ti) {
		return templateDao.templateTitleImg(sqlSession, ti);
	}
	
	
}
