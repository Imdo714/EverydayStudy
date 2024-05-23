package com.matcha.mvc.chatting.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket 
public class WebSocketConfiguration implements WebSocketConfigurer {
	
//	@EnableWebSocket 는 웹 소켓 관련 설정을 자동으로 해줌
//	setAllowedOrigins 는 도메인이 다른 서버에서도 접속 가능 하도록 CORS : setAllowedOrigins("*") 설정을 추가.

	private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/study/chat")
        .setAllowedOrigins("*");
    }
}
