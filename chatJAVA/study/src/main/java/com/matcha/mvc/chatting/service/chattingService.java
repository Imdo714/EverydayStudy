package com.matcha.mvc.chatting.service;

import java.util.ArrayList;

import com.matcha.mvc.chatting.vo.ChatRoom;
import com.matcha.mvc.chatting.vo.Chatting;

public interface chattingService {

	// 채팅 방이 있는지 없는 확인 
	ArrayList<ChatRoom> selectRoomNo(int userNo);
	
	// 채팅방 만들기
	int chatRoomInsert(int userNo);
	
	// 데이터베이스에 채팅 저장
	int insertMessage(Chatting vo);
}
