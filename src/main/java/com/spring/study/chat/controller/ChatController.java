package com.spring.study.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.study.chat.model.ChatUser;
import com.spring.study.chat.service.ChatUserService;
import com.spring.study.model.User;
import com.spring.study.user.service.UserFindService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/talk")
@Log4j
public class ChatController {

	@Autowired
	ChatUserService chatUserService;
	
	@GetMapping({"", "/"})
	public String getListPage() {
		return "chat/list";
	}
	
	@GetMapping("/{chatRoomId}")
	public String showChatRoom(@PathVariable(value = "chatRoomId") int chatRoomId, 
							   HttpSession session, 
							   Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			log.warn("NOT LOGINED");;
			return "redirect:/";
		}
		

		ChatUser chatUser = new ChatUser(chatRoomId, user.getSeqId());
		if (chatUserService.selectCountByChatRoomIdAndUserSeqId(chatUser) == 0) {
			log.warn("NOT PERMISSION");
			return "redirect:/";
		}
		
		model.addAttribute("chatRoomId", chatRoomId);
		
		return "chat/talk";
	}
	
}
