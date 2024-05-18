package com.matcha.mvc.template.service;

import java.util.ArrayList;

import com.matcha.mvc.common.vo.PageInfo;
import com.matcha.mvc.template.vo.Template;
import com.matcha.mvc.template.vo.TemplateImg;
import com.matcha.mvc.template.vo.TemplateReply;

public interface templateService {

	// 템플릿 올리기
	int templateInsert(Template t, int userNo);
	
	// 템플릿 썸내일 올리기
	int templateTitleImg(TemplateImg ti);
	
	// 템플릿 리스트 총 갯수 
	int selectListCount();
	
	// 템플릿 리스트 정보 가져오기 
	ArrayList<Template> selectTemplateList(PageInfo pi);
	
	// 디테일 템플릿 정보
	ArrayList<Template> detailTemplate(int tno);
	
	// 템플릿 댓글 가져오기 
	ArrayList<TemplateReply> detailReplyTemplate(PageInfo pi, int tno);
	
	// 댓글 총 갯수 
	int selectReplyCount(int tno);
	
	// 템플릿 수정 업데이트 
	int updateTemplate(Template t);
	
	// 댓글 작성
	int replyInsert(TemplateReply r, int userNo);
	
	// 댓글 삭제 
	int replyDelt(int templateReplyNo);
	
	// 댓글 수정
	int replyUpdate(TemplateReply tr);
	
	// 마에피이지 템플릿
	ArrayList<Template> selectUserTemplate(int userNo);
	
	// 템플릿 이미지 사진 정보 다 가져오기
	ArrayList<TemplateImg> TemplateImgAll(int templateNo);
	
	// 템플릿 관련 모두 삭제
	int templateAllDelte(int templateNo);
	
	// 썸머 노트 사진 이름 저장하기 
	int summerImgName(TemplateImg sti);
	
	// 회원 템플릿 갯수 
	int userTemplateCount(int userNo);
	
	// 회원 댓글 갯수 
	int userReplyCount(int userNo);
}
