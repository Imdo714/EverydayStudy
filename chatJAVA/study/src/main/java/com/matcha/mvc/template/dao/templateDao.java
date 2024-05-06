package com.matcha.mvc.template.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;

@Repository
public class templateDao {

	// 템플릿 올리기
	public int templateInsert(SqlSessionTemplate sqlSession, Template t, int userNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNo", userNo);
		params.put("t", t);
		
		return sqlSession.insert("TemplateMapper.templateInsert", params);
	}
	
	// 템플릿 썸네일 올리기
	public int templateTitleImg(SqlSessionTemplate sqlSession, TemplateImg ti) {
		return sqlSession.insert("TemplateMapper.templateTitleImg", ti);
	}
	
	
}
