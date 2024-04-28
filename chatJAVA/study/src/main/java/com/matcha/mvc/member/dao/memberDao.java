package com.matcha.mvc.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.matcha.mvc.member.vo.Member;

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
	
}
