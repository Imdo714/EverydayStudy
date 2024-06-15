package com.matcha.mvc.chatting.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.matcha.mvc.chatting.service.chattingService;
import com.matcha.mvc.chatting.vo.Chatting;
import com.matcha.mvc.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ChatServer extends TextWebSocketHandler{ 
	 
	// socket통신은 서버와 클라이언트가 1:N으로 관계를 맺는다. 
	// 서버에는 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler의 작성이 필요하다.
	// 핸들러는 TextWebSocketHandler를 상속받아서 작성하고, Client로부터 받은 메시지를 Console log에 출력하고 Client로 환영 메시지를 보내는 역할을 한다.
	
	@Autowired
	chattingService chattingService;
	
	private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception { // 커넥션이 연결됬을때 

//		String nick = (String)session.getAttributes().get("nick");
		Member user = (Member)session.getAttributes().get("loginUser");
		int roomNo = (int)session.getAttributes().get("roomNo");
		
		int userNo = user.getUserNo();
		
		Map<Integer, WebSocketSession> userSession = new ConcurrentHashMap();
		userSession.put(userNo, session);
		
		log.info("{} 몇번 방??", roomNo);
		log.info("{} 진짜 연결됨!!!", userNo);
		
//		userSessions.put(nick, session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception { // 메시지를 보냈을때 
		
//		String nick = (String)session.getAttributes().get("nick");
		Member user = (Member)session.getAttributes().get("loginUser");
		int roomNo = (int)session.getAttributes().get("roomNo");
		
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject(); // js에서 
		log.info("{} 누구야",obj);
		
		int userNo = user.getUserNo();
		
		Chatting vo = new Chatting();
		vo.setRoomNo(roomNo);
		vo.setChattingContent(obj.get("message").getAsString()); // 채팅 메세지
		vo.setUserNo(user.getUserNo());						// 보낸 사람 
//		vo.setChattingDate(new Date(0).toLocaleString()); // 채팅 날짜/시간
		
		sendMEssageToUser(roomNo, vo);
//		sendMEssageToUser(obj.get("target").getAsString(), vo); // target안에 nick이 들어있음 이렇게 하지말고 채팅방 아이디를 보내서 채팅방 아이디에 채팅 저장 해야함
	}
	
	private void sendMEssageToUser(int roomNo, Chatting msgVo) {
		log.info("{} 결과", msgVo);
		log.info("{} 마지막", roomNo);
		
		chattingService.insertMessage(msgVo); // 데이터베이스 채팅 저장 
		
		WebSocketSession targetSession = userSessions.get(roomNo); // WebSocketSession에서 roomNo에 해당하는 세션을 가져옵니다.
		
		WebSocketSession mySession = userSessions.get(msgVo.getUserNo()); // 메시지를 보낸 사용자의 세션을 가져옵니다.
		
		
		if(targetSession != null && targetSession.isOpen()) {  // 대상 사용자의 세션이 열려 있고 유효할 경우
			String str = new Gson().toJson(msgVo); // 메시지 객체를 JSON 문자열로 변환합니다.
			TextMessage msg = new TextMessage(str);
			try {  // 자신의 세션과 대상 사용자의 세션에 메시지를 전송합니다.
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
