package com.matcha.mvc.member.service;

import com.matcha.mvc.member.vo.Member;

public interface memberService {

	// 회원가입
	int insertMember(Member m);
	
	// 아이디 조회 
	int selectMember(String userId);
	
	// 로그인 
	Member loginMember(String userId);
	
}
