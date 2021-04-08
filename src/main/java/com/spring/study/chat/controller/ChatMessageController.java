package com.spring.study.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.study.chat.model.MessagePage;
import com.spring.study.chat.model.OutputMessagePage;
import com.spring.study.chat.service.MessageService;
import com.spring.study.model.User;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ChatMessageController {
	
	@Autowired
	MessageService service;
	
	@GetMapping("/message")
	public OutputMessagePage getMessages(MessagePage info, HttpSession session) {
		User user = (User) session.getAttribute("user");
		info.setSender(user.getSeqId());
		
		OutputMessagePage result = new OutputMessagePage();
		result.setMessages(service.selectMessages(info));
		result.setNext(info.getPageStart(), info.getPageCount(), service.selectTotalCountOfMessages(info));
		log.info("result=" + result);
		
		return result;
	}
	
}
