package com.matcha.mvc.member.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.matcha.mvc.member.vo.Member;
import com.matcha.mvc.member.vo.MemberImg;

@Repository
public class memberDao {

	// 회원가입
	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	// 아이디 조회
	public int selectMember(SqlSessionTemplate sqlSession, String userId) {
		Integer result = sqlSession.selectOne("memberMapper.selectMember", userId);
		return (result != null) ? 1 : 0;
	}
	
	// 로그인
	public Member loginMember(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.selectOne("memberMapper.loginMember", userId);
	}
	
	// 회원가입시 기본프로필 등록
	public int defaultImg(SqlSessionTemplate sqlSession, MemberImg mi) {
		return sqlSession.insert("memberMapper.defaultImg", mi);
	}
	
	// myPage 프로필 찾기 
	public MemberImg selectMemberImg(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("memberMapper.selectMemberImg", userNo);
	}
	
	// 회원 정보 가져오기
	public Member selectUser(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("memberMapper.selectUser", userNo);
	}
	
	// 마이페이지 이미지 업데이트
	public int updateImg(SqlSessionTemplate sqlSession, MemberImg mi, int userNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNo", userNo);
		params.put("mi", mi);
		
		return sqlSession.update("memberMapper.updateImg", params);
	}
	
	// 마이페이지 회원 정보 업데이트 
	public int updateMember(SqlSessionTemplate sqlSession, Member m, int userNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNo", userNo);
		params.put("m", m);
		
		return sqlSession.update("memberMapper.updateMember", params);
	}
	
}
