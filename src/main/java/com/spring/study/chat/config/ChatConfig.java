package com.spring.study.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.spring.study.chat.handler.MessageHandler;

@Configuration
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
			.addHandler(new MessageHandler(), "/ws")
			.addInterceptors(new HttpSessionHandshakeInterceptor())
			.withSockJS();
	}
	
	
}
