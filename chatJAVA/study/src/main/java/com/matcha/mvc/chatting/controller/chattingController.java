package com.matcha.mvc.chatting.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.chatting.service.chattingService;
import com.matcha.mvc.chatting.vo.ChatRoom;
import com.matcha.mvc.member.vo.Member;
import com.matcha.mvc.template.vo.Template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class chattingController {

	@Autowired
	chattingService chattingService;
	
	
// 채팅 로그인
	@RequestMapping(value="/chat.ch")
    public String chatBot(HttpSession session) {
		
        return "chatting/chatBot";
    }
	
	 
// 채팅방
//	@RequestMapping(value="/chatting.ch")
//    public String chat(HttpSession session, String nick) {
//		
//		session.setAttribute("nick", nick);
//
//		Member m = (Member) session.getAttribute("loginUser");
//		int userNo = m.getUserNo();
//        
//		log.info("{} 컨트롤러 연결됨", nick);
//		
//        return "chatting/chat";
//    }
	@RequestMapping(value="/chatting.ch")
    public String chat(HttpSession session) { // 채팅방을 만들고 드가자 있으면 그거 사용 
		
		Member m = (Member) session.getAttribute("loginUser");
		int userNo = m.getUserNo();
		
		ArrayList<ChatRoom> res = chattingService.selectRoomNo(userNo);
		
		int roomNo = 0;
		
		for(ChatRoom room : res) {
			System.out.println("채팅방 몇개: " + room);
			roomNo = room.getRoomNo();
		}
		
		if(roomNo != 0) {
			session.setAttribute("roomNo", roomNo); // 세션에 채팅방 번호 넣어줌 
		} else {
			int resR = chattingService.chatRoomInsert(userNo); // 채팅방 생성
			System.out.println("생성된 채팅방 번호: " + resR);
			session.setAttribute("roomNo", resR); // 세션에 채팅방 번호 넣어줌 
		}
		
        
		log.info("{} 컨트롤러 연결됨", userNo);
		
        return "chatting/chat";
    }
}
