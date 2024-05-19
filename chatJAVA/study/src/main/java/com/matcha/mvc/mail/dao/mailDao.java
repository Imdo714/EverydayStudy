package com.matcha.mvc.mail.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.matcha.mvc.mail.vo.Mail;
import com.matcha.mvc.template.vo.Template;

@Repository
public class mailDao {

	// 메일 DB에 기록
	public int sendMail(SqlSessionTemplate sqlSession, Mail m, int userNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNo", userNo);
		params.put("m", m);
		
		return sqlSession.insert("mailMapper.sendMail", params);
	}
}
