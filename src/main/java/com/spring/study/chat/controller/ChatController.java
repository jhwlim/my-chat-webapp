package com.spring.study.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.study.model.User;
import com.spring.study.user.service.UserFindService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/talk")
@Log4j
public class ChatController {

	@Autowired
	UserFindService userFindService;
	
	@GetMapping({"", "/"})
	public String getListPage() {
		return "chat/list";
	}
	
	@GetMapping("/{id}")
	public String getTalkPage(@PathVariable(value = "id") String id, HttpSession session, Model model) {
		log.info("id=" + id);
		
		User user = (User) session.getAttribute("user");
		if (user == null || user.getId().equals(id)) {
			log.warn("NOT LOGINED or CAN'T NOT SEND TO YOURSELF");;
			return "redirect:/talk";
		}
		
		User receiver = userFindService.selectUserById(id);
		if (receiver == null) {
			log.warn(id + " is NOT FOUND");;
			return "redirect:/talk";
		}
		
		model.addAttribute("receiver", receiver);
		return "chat/talk";
	}
	
}
