package com.spring.study.login.mapper;

import com.spring.study.model.User;

public interface LoginMapper {
	
	public User selectUserById(String id);
	
}
