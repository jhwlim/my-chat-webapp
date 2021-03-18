package com.spring.study.chat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.spring.study.chat.model.Message;
import com.spring.study.chat.model.OutputMessage;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class MessageController {

	@MessageMapping("/chat") // 클라이언트 요청 경로
	@SendTo("/topic/messages") 
	public OutputMessage send(Message message) throws Exception {
		log.info("message=" + message);
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		return new OutputMessage(message.getFrom(), message.getText(), time);
	}
	
}
