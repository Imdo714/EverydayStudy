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
	
	private final Map<Integer, WebSocketSession> userSessions = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception { // 커넥션이 연결됬을때 
		Member user = (Member)session.getAttributes().get("loginUser");
		int roomNo = (int)session.getAttributes().get("roomNo");
		
		int userNo = user.getUserNo();
		
		userSessions.put(userNo, session);
		
		log.info("{} 몇번 방??", roomNo);
		log.info("{} 진짜 연결됨!!!", userNo);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception { // 메시지를 보냈을때 
		
		Member user = (Member)session.getAttributes().get("loginUser");
		int roomNo = (int)session.getAttributes().get("roomNo");
		
		JsonObject obj = new JsonParser().parse(message.getPayload()).getAsJsonObject(); // js에서 
		
		int userNo = user.getUserNo();
		
		Chatting vo = new Chatting();
		vo.setRoomNo(roomNo);
		vo.setChattingContent(obj.get("message").getAsString()); // 채팅 메세지
		vo.setUserNo(user.getUserNo());						// 보낸 사람 
		
		sendMEssageToUser(roomNo, vo);
	}
	
	private void sendMEssageToUser(int roomNo, Chatting msgVo) {
		
		chattingService.insertMessage(msgVo); // 데이터베이스 채팅 저장 

        WebSocketSession targetSession = userSessions.get(roomNo); // roomNo에 해당하는 세션을 가져옴
        
        WebSocketSession mySession = userSessions.get(msgVo.getUserNo()); // 메시지를 보낸 사용자의 세션을 가져옴
		
        
        if (targetSession != null && targetSession.isOpen()) { // 대상 사용자의 세션이 열려 있고 유효할 경우
            String str = new Gson().toJson(msgVo); // 메시지 객체를 JSON 문자열로 변환
            TextMessage msg = new TextMessage(str);
            try { // 자신의 세션과 대상 사용자의 세션에 메시지 전송
                if (mySession != null && mySession.isOpen()) {
                    mySession.sendMessage(msg);
                }
                targetSession.sendMessage(msg);
            } catch (IOException e) {
                log.error("메시지 전송 중 오류 발생: {}", e.getMessage());
                e.printStackTrace();
            }
        } else {
            log.error("RoomNo {}에 해당하는 세션이 없습니다.", roomNo);
        }
        
//        if (targetSession != null && targetSession.isOpen()) { // 대상 사용자의 세션이 열려 있고 유효한지 확인
//            String str = new Gson().toJson(msgVo);    // msgVo 객체를 JSON 문자열로 변환합니다.
//            TextMessage msg = new TextMessage(str);   // JSON 문자열을 사용해 TextMessage 객체를 생성합니다.
//            try {
//                if (mySession != null && mySession.isOpen()) { // 메시지를 보낸 사용자의 세션이 null이 아니고 열려 있는지 확인합니다.
//                    if (mySession != targetSession) { //mySession과 targetSession이 같은 세션인지 확인하고, 동일한 세션에 두 번 메시지를 보내지 않도록 합니다.
//                        mySession.sendMessage(msg); // 메시지를 보낸 사용자의 세션에 메시지를 전송합니다. 이는 사용자가 자신의 메시지를 볼 수 있도록 하기 위함입니다.
//                    }
//                }
//                targetSession.sendMessage(msg); // 대상 사용자의 세션에 메시지를 전송합니다. 이는 메시지를 받는 사용자가 메시지를 볼 수 있도록 하기 위함입니다.
//            } catch (IOException e) {
//                log.error("메시지 전송 중 오류 발생: {}", e.getMessage());
//                e.printStackTrace();
//            }
//        } else {
//            log.error("RoomNo {}에 해당하는 세션이 없습니다.", roomNo);
//        }
        
		log.info("타겟 세션: {}", targetSession);
		log.info("나의 세션: {}", mySession);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception { // 연결이 끝겼을때
		 Member user = (Member) session.getAttributes().get("loginUser");
        int userNo = user.getUserNo();

        userSessions.remove(userNo);
        log.info("UserNo {}의 세션이 닫혔습니다.", userNo);
	}
	


}
