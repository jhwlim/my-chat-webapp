package com.spring.study.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app"); // 클라이언트로부터 메시지를 받을 prefix 설정
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { // 클라이언트에서 연결할 경로 설정, endpoint 설정
		registry.addEndpoint("/chat");
		registry.addEndpoint("/chat").withSockJS();
	}
	
}
