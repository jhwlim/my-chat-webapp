package com.spring.study.user.service;

import com.spring.study.model.User;

public interface UserFindService {
	
	public User selectUserById(String id);
	
}
