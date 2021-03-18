package com.spring.study.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/talk")
@Log4j
public class ChatController {

	@GetMapping({"", "/"})
	public String getListPage() {
		return "chat/list";
	}
	
	@GetMapping("/{id}")
	public String getTalkPage(@PathVariable(value = "id") String id) {
		log.info("id=" + id);
		return "chat/talk";
	}
	
}
