package com.matcha.mvc.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.member.dao.memberDao;
import com.matcha.mvc.member.vo.Member;
import com.matcha.mvc.member.vo.MemberImg;

@Service
public class memberServiceImpl implements memberService{

	@Autowired
	private memberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	@Override
	public int insertMember(Member m) {
		return memberDao.insertMember(sqlSession, m);
	}

	@Override
	public int selectMember(String userId) {
		return memberDao.selectMember(sqlSession, userId);
	}

	@Override
	public Member loginMember(String userId) {
		return memberDao.loginMember(sqlSession, userId);
	}

	@Override
	public int defaultImg(MemberImg mi) {
		return memberDao.defaultImg(sqlSession, mi);
	}

	@Override
	public MemberImg selectMemberImg(int userNo) {
		return memberDao.selectMemberImg(sqlSession, userNo);
	}

	@Override
	public Member selectUser(int userNo) {
		return memberDao.selectUser(sqlSession, userNo);
	}

}
