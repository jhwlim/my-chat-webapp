package com.spring.study.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.study.model.User;
import com.spring.study.user.service.UserFindService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@Autowired
	UserFindService ufs;
	
	@GetMapping(value = "/")
	public String checkLoginSession(HttpSession session) {
		User loginUser = (User) session.getAttribute("user");
		
		return loginUser == null ? "index" : "chat/list";
	}
	
	@PostMapping(value = "/login")
	public String checkLogin(User user, HttpSession session) {
		log.info("user=" + user);
		
		User result = ufs.selectUserById(user.getId());
		log.info("result=" + result);
		if (result != null) {
			session.setAttribute("user", result);
		} else {
			log.warn("LOGIN FAIL");
		}
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/logout")
	public String performLogout(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		log.info("LOGOUT COMPLETE");
		
		return "redirect:/";
	}
	
}
