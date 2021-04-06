package com.spring.study.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.spring.study.chat.handler.ChatMessageHandler;

@Configuration
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
			.addHandler(chatMessageHandler(), "/ws/{userId}")
			.addInterceptors(chatHandshakeInterceptor())
			.withSockJS();
	}

	@Bean
	public ChatMessageHandler chatMessageHandler() {
		return new ChatMessageHandler();
	}
	
	@Bean
	public ChatHandshakeInterceptor chatHandshakeInterceptor() {
		return new ChatHandshakeInterceptor();
	}
}
