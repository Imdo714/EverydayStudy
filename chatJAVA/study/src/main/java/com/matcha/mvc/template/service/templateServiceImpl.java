package com.matcha.mvc.template.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.template.dao.templateDao;
import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;
import com.matcha.mvc.template.vo.TemplateReply;

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

	@Override
	public int selectListCount() {
		return templateDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Template> selectTemplateList(PageInfo pi) {
		return templateDao.selectTemplateList(sqlSession, pi);
	}

	@Override
	public ArrayList<Template> detailTemplate(int tno) {
		return templateDao.detailTemplate(sqlSession, tno);
	}
	
	@Override
	public ArrayList<TemplateReply> detailReplyTemplate(PageInfo pi, int tno) {
		return templateDao.detailReplyTemplate(sqlSession, pi, tno);
	}
	
	@Override
	public int selectReplyCount(int tno) {
		return templateDao.selectReplyCount(sqlSession, tno);
	}

	@Override
	public int updateTemplate(Template t) {
		return templateDao.updateTemplate(sqlSession, t);
	}

	@Override
	public int replyInsert(TemplateReply r, int userNo) {
		return templateDao.replyInsert(sqlSession, r, userNo);
	}

	@Override
	public int replyDelt(int templateReplyNo) {
		return templateDao.replyDelt(sqlSession, templateReplyNo);
	}

	@Override
	public int replyUpdate(TemplateReply tr) {
		return templateDao.replyUpdate(sqlSession, tr);
	}

	@Override
	public ArrayList<Template> selectUserTemplate(int userNo) {
		return templateDao.selectUserTemplate(sqlSession, userNo);
	}

	@Override
	public int templateAllDelte(int templateNo) {
		return templateDao.templateAllDelte(sqlSession, templateNo);
	}






	
}
