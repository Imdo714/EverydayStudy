package com.matcha.mvc.chatting.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.matcha.mvc.chatting.vo.ChatRoom;
import com.matcha.mvc.chatting.vo.Chatting;
import com.matcha.mvc.template.vo.TemplateImg;

@Repository
public class chattingDao {

	// 채팅 방이 있는지 없는 확인 
	public ArrayList<ChatRoom> selectRoomNo(SqlSessionTemplate sqlSession, int userNo) {
		return (ArrayList)sqlSession.selectList("ChattingMapper.selectRoomNo", userNo);
	}
	
	// 채팅방 만들기 
	public int chatRoomInsert(SqlSessionTemplate sqlSession, int userNo) {
		int a = sqlSession.insert("ChattingMapper.chatRoomInsert", userNo);
		ArrayList<ChatRoom> list = (ArrayList)sqlSession.selectList("ChattingMapper.selectRoomNo", userNo);
		
		int roomNo = list.get(0).getRoomNo();	
		
		return roomNo;
	}
	
	public int insertMessage(SqlSessionTemplate sqlSession, Chatting vo) {
		return sqlSession.insert("ChattingMapper.insertMessage", vo);
	}
	
}
