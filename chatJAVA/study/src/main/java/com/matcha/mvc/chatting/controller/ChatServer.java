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
import com.matcha.mvc.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ChatServer extends TextWebSocketHandler{ 
	 
	// socket통신은 서버와 클라이언트가 1:N으로 관계를 맺는다. 
	// 서버에는 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler의 작성이 필요하다.
	// 핸들러는 TextWebSocketHandler를 상속받아서 작성하고, Client로부터 받은 메시지를 Console log에 출력하고 Client로 환영 메시지를 보내는 역할을 한다.
	
	private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception { // 커넥션이 연결됬을때 
		
//		String nick = (String)session.getAttributes().get("nick");
		Member user = (Member)session.getAttributes().get("loginUser");
		
		int userNo = user.getUserNo();
		
		Map<Integer, WebSocketSession> userSession = new ConcurrentHashMap();
		userSession.put(userNo, session);
		
		log.info("{} 진짜 연결됨!!!", userNo);
		
//		userSessions.put(nick, session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception { // 메시지를 보냈을때 
		
		String nick = (String)session.getAttributes().get("nick");
		Member user = (Member)session.getAttributes().get("loginUser");
		
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject();
		log.info("{} 누구야",obj);
		
		int userNo = user.getUserNo();
		
		Chatting vo = new Chatting();
		vo.setMsg(obj.get("message").getAsString());
		vo.setName(user.getUserNo());
		vo.setTime(new Date(0).toLocaleString());
		
		sendMEssageToUser(obj.get("target").getAsString(), vo);
//		sendMEssageToUser(obj.get("target").getAsString(), vo);
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
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception { // 연결이 끝겼을때

		String nick = (String)session.getAttributes().get("nick");

		log.info("{} 연결끊김", nick);
		userSessions.remove(nick);
	}
	


}
