package com.spring.study.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.study.model.User;
import com.spring.study.user.mapper.UserFindMapper;
import com.spring.study.user.service.UserFindService;

@Service
public class UserFindServiceImpl implements UserFindService {

	@Autowired
	UserFindMapper mapper;
	
	@Override
	public User selectUserById(String id) {
		return mapper.selectUserById(id);
	}
	
}
