package com.spring.study.login.service;

import com.spring.study.model.User;

public interface LoginService {
	
	public User selectUserById(String id);
	
}
