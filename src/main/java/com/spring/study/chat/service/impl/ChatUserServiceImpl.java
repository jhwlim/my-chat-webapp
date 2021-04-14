package com.spring.study.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.study.chat.mapper.ChatUserMapper;
import com.spring.study.chat.model.ChatRoom;
import com.spring.study.chat.model.ChatUser;
import com.spring.study.chat.service.ChatUserService;

import lombok.extern.log4j.Log4j;

@Service
public class ChatUserServiceImpl implements ChatUserService {

	@Autowired
	ChatUserMapper mapper;
	
	@Override
	public Integer selectChatRoomId(int user1, int user2) {
		return mapper.selectChatRoomId(user1, user2);
	}

	@Override
	public int selectCountByChatRoomIdAndUserSeqId(ChatUser chatUser) {
		return mapper.selectCountByChatRoomIdAndUserSeqId(chatUser);
	}

}
