package com.spring.study.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.study.login.mapper.LoginMapper;
import com.spring.study.login.service.LoginService;
import com.spring.study.model.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper mapper;
	
	@Override
	public User selectUserById(String id) {
		return mapper.selectUserById(id);
	}
	
}
