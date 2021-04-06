package com.spring.study.chat.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
		int seqId = getUser(session).getSeqId();
		
		Set<WebSocketSession> set = sessions.get(seqId);
		if (set == null) {
			set = new HashSet<>();
		}
		set.add(session);
		sessions.put(seqId, set);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("session=" + session);
		User user = getUser(session);
		
		String payload = message.getPayload();
		InputMessage m = gson.fromJson(payload, InputMessage.class);
		log.info("payload=" + m);
		
		// 메시지 DB에 저장하고, 저장된 메시지 정보를 DB에서 가져오기 
		m.setSender(user.getSeqId());
		
		OutputMessage output = service.insertMessage(m);
		log.info("output message=" + output);
		
		// '나'의 세션 중 해당 상대방 아이디 세션에게 메시지 보내기
		for (WebSocketSession s : sessions.get(user.getSeqId())) {
			String to = getToUserId(s);
			if (to.equals(m.getReceiverId())) {
				s.sendMessage(new TextMessage(gson.toJson(output)));				
			}
		}
		
		// 상대방 아이디의 세션 중 상대방이 '나'인 세션에게 메시지 보내기
		Set<WebSocketSession> toSessions = sessions.get(m.getReceiver());
		if (toSessions != null) {
			for (WebSocketSession s : toSessions) {
				String to = getToUserId(s);
				if (to.equals(user.getId())) {
					s.sendMessage(new TextMessage(gson.toJson(output)));					
				}
			}	
		}
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("disconnected sessionId=" + session.getId());
		log.info("status" + status);
		
		int seqId = getUser(session).getSeqId();
		Set<WebSocketSession> set = sessions.get(seqId);
		set.remove(session);
		if (set.size() == 0) {
			sessions.remove(seqId);
		}
		
		log.info("session count=" + sessions.size());
	}
	
	
	private User getUser(WebSocketSession session) {
		return (User) session.getAttributes().get("sender");
	}
	
	private String getToUserId(WebSocketSession session) {
		return (String) session.getAttributes().get("receiver");
	}
}
