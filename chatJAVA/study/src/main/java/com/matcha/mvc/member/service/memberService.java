package com.matcha.mvc.member.service;

import com.matcha.mvc.member.vo.Member;
import com.matcha.mvc.member.vo.MemberImg;

public interface memberService {

	// 회원가입
	int insertMember(Member m);
	
	// 아이디 조회 
	int selectMember(String userId);
	
	// 로그인 
	Member loginMember(String userId);
	
	// 회원가입시 기본프로필 등록
	int defaultImg(MemberImg mi);
	
	// myPage 프로필 찾기 
	MemberImg selectMemberImg(int userNo);
	
	// 회원 정보 가져오기
	Member selectUser(int userNo);
	
	// 마이페이지 이미지 업데이트
	int updateImg(MemberImg mi, int userNo);
	
	// 마이페이지 회원 정보 업데이트 
	int updateMember(Member m, int userNo);
}
