package com.spring.study.chat.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.spring.study.chat.model.InputMessage;
import com.spring.study.chat.model.OutputMessage;
import com.spring.study.chat.service.MessageService;
import com.spring.study.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
public class ChatMessageHandler extends TextWebSocketHandler {
	
	Map<Integer, Set<WebSocketSession>> sessions = new HashMap<>();
	
	Gson gson = new Gson();
	
	@Autowired
	MessageService service;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("connected sessionId=" + session.getId());
		int chatRoomId = getChatRoomIdFromSession(session);
		
		Set<WebSocketSession> set = sessions.get(chatRoomId);
		if (set == null) {
			set = new HashSet<>();
		}
		set.add(session);
		sessions.put(chatRoomId, set);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("session=" + session);
		User user = getUserFromSession(session);
		int chatRoomId = getChatRoomIdFromSession(session);
		
		String payload = message.getPayload();
		InputMessage m = gson.fromJson(payload, InputMessage.class);
		
		// 메시지 DB에 저장하고, 저장된 메시지 정보를 DB에서 가져오기 
		m.setSender(user.getSeqId());
		m.setChatRoomId(chatRoomId);
		
		OutputMessage output = service.insertMessage(m);
		output.setSenderId(user.getId());
		log.info("output message=" + output);
		
		Set<WebSocketSession> set = sessions.get(chatRoomId);
		for (WebSocketSession s : set) {
			s.sendMessage(new TextMessage(gson.toJson(output)));
		}	
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("disconnected sessionId=" + session.getId());
		log.info("status" + status);
		
		int chatRoomId = getChatRoomIdFromSession(session);
		Set<WebSocketSession> set = sessions.get(chatRoomId);
		set.remove(session);
		if (set.size() == 0) {
			sessions.remove(chatRoomId);
		}
	}
	

	private User getUserFromSession(WebSocketSession session) {
		return (User) session.getAttributes().get("user");
	}
	
	private int getChatRoomIdFromSession(WebSocketSession session) {
		return (int) session.getAttributes().get("chatRoomId");
	}
}
