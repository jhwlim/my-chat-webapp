package com.spring.study.chat.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.study.chat.model.ChatUser;

public interface ChatUserMapper {

	public int selectCountByChatRoomIdAndUserSeqId(ChatUser chatUser);
	
	public Integer selectChatRoomId(@Param("user1") int user1, @Param("user2") int user2);
	
}
