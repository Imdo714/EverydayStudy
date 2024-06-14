package com.matcha.mvc.chatting.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.chatting.service.chattingService;
import com.matcha.mvc.member.vo.Member;

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
	@RequestMapping(value="/chatting.ch")
    public String chat(HttpSession session, String nick) {
		
		session.setAttribute("nick", nick);

		Member m = (Member) session.getAttribute("loginUser");
		int userNo = m.getUserNo();
        
		log.info("{} 컨트롤러 연결됨", nick);
		
        return "chatting/chat";
    }
}
