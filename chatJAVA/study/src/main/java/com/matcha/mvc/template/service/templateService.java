package com.matcha.mvc.template.service;

import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;

public interface templateService {

	// 템플릿 올리기
	int templateInsert(Template t, int userNo);
	
	// 템플릿 썸내일 올리기
	int templateTitleImg(TemplateImg ti);
}
