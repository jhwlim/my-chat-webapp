package com.spring.study.chat.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.spring.study.chat.model.Message;
import com.spring.study.chat.model.OutputMessage;

import lombok.extern.log4j.Log4j;

@Log4j
public class MessageHandler extends TextWebSocketHandler {
	
	Set<WebSocketSession> sessions = new HashSet<>();
	
	Gson gson = new Gson();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("connected sessionId=" + session.getId());
	
		sessions.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("session=" + session);
		
		String payload = message.getPayload();
		Message m = gson.fromJson(payload, Message.class);
		log.info("payload=" + m);
		
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		OutputMessage output = new OutputMessage(m.getFrom(), m.getText(), time);
		
		for (WebSocketSession s : sessions) {
			s.sendMessage(new TextMessage(gson.toJson(output)));			
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("disconnected sessionId=" + session.getId());
		log.info("status" + status);
		
		sessions.remove(session);
		log.info("session count=" + sessions.size());
	}
}
