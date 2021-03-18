package com.spring.study.user.mapper;

import com.spring.study.model.User;

public interface UserFindMapper {
	
	public User selectUserById(String id);
	
}
