package com.spring.study.chat.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.spring.study.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
public class ChatHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(
			ServerHttpRequest request, 
			ServerHttpResponse response, 
			WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		setAttributes(request, attributes);
		
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	private void setAttributes(ServerHttpRequest request, Map<String, Object> attributes) {
		ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
		HttpServletRequest req = ssreq.getServletRequest();
		User user = (User) req.getSession().getAttribute("user");
		log.info("sender=" + user);
		
		int chatRoomId = Integer.parseInt(getChatRoomIdFromURI(req.getRequestURI()));
		log.info("chatRoomId=" + chatRoomId);
		
		attributes.put("user", user);
		attributes.put("chatRoomId", chatRoomId);
	}
	
	private String getChatRoomIdFromURI(String uri) {
		String prefix = "/ws/";
		int idx = uri.indexOf(prefix) + prefix.length();
		return uri.substring(idx, uri.indexOf("/", idx));
	}
}
