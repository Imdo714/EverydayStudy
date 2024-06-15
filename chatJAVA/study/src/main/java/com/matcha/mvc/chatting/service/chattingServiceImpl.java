package com.matcha.mvc.chatting.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matcha.mvc.chatting.dao.chattingDao;
import com.matcha.mvc.chatting.vo.ChatRoom;
import com.matcha.mvc.chatting.vo.Chatting;

@Service
public class chattingServiceImpl implements chattingService{

	@Autowired
	private chattingDao chattingDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	@Override
	public ArrayList<ChatRoom> selectRoomNo(int userNo) { 
		return chattingDao.selectRoomNo(sqlSession, userNo);
	}

	@Override
	public int chatRoomInsert(int userNo) {
		return chattingDao.chatRoomInsert(sqlSession, userNo);
	}

	@Override
	public int insertMessage(Chatting vo) {
		return chattingDao.insertMessage(sqlSession, vo);
	}

	
	
}
