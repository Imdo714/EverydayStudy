package com.matcha.mvc.chatting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.matcha.mvc.chatting.service.chattingService;

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
		
		System.out.println("aaaa" + nick);
		
		log.info("{} 연결됨", nick);
		
        return "chatting/chat";
    }
}
