package com.spring.study.chat.mapper;

import java.util.List;

import com.spring.study.chat.model.InputMessage;
import com.spring.study.chat.model.MessagePage;
import com.spring.study.chat.model.OutputMessage;

public interface MessageMapper {

	public void insertMessage(InputMessage message);
	
	public void insertText(InputMessage message);
	
	public OutputMessage selectMessageById(int id);
	
	public List<OutputMessage> selectMessagesByChatRoomId(MessagePage info);
	
	public int selectTotalCountOfMessageByChatRoomId(int chatRoomId);
	
}
