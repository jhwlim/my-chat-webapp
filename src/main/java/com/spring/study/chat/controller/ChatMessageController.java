package com.spring.study.chat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.study.chat.model.MessagePage;
import com.spring.study.chat.model.OutputMessage;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ChatMessageController {
	
	@GetMapping("/message")
	public List<OutputMessage> getMessages(MessagePage page) {
		// log.info("page info=" + page);
		return null;
	}
	
}
