package com.matcha.mvc.template.service;

import java.util.ArrayList;

import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;

public interface templateService {

	// 템플릿 올리기
	int templateInsert(Template t, int userNo);
	
	// 템플릿 썸내일 올리기
	int templateTitleImg(TemplateImg ti);
	
	// 템플릿 리스트 총 갯수 
	int selectListCount();
	
	// 템플릿 리스트 정보 가져오기 
	ArrayList<Template> selectTemplateList(PageInfo pi);
}
