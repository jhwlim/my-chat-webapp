package com.spring.study.chat.service;

import com.spring.study.chat.model.ChatUser;

public interface ChatUserService {

	public int selectCountByChatRoomIdAndUserSeqId(ChatUser chatUser);
	
	public Integer selectChatRoomId(int user1, int user2);
	
}
