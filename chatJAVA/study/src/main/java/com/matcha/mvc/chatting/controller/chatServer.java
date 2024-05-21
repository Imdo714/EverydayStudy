package com.matcha.mvc.chatting.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.matcha.mvc.chatting.vo.Chatting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class chatServer extends TextWebSocketHandler{
	
	private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap();
	
	public chatServer() {
		super();
		this.log.info("소켓 핸들러 생성!");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		System.out.println("afterConnectionEstablished" + session);
		
		String nick = (String)session.getAttributes().get("nick");
		
		log.info("{} 연결됨", nick);
		
		userSessions.put(nick, session);
	}


	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String nick = (String)session.getAttributes().get("nick");
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject();
		
		Chatting vo = new Chatting();
		vo.setMsg(obj.get("message").getAsString());
		vo.setName(nick);
		vo.setTime(new Date(0).toLocaleString());
		
		sendMEssageToUser(obj.get("target").getAsString(), vo);
	}
	
	private void sendMEssageToUser(String nick, Chatting msgVo) {
		WebSocketSession targetSession = userSessions.get(nick);
		WebSocketSession mySession = userSessions.get(msgVo.getName());
		
		
		if(targetSession != null && targetSession.isOpen()) {
			String str = new Gson().toJson(msgVo);
			TextMessage msg = new TextMessage(str);
			try {
				mySession.sendMessage(msg);
				targetSession.sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed" + session);
		System.out.println("afterConnectionClosed22" + status);
		String nick = (String)session.getAttributes().get("nick");
		log.info("{} 연결끊김", nick);
		userSessions.remove(nick);
	}

}
