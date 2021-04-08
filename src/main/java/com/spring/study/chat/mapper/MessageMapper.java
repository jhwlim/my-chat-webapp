package com.spring.study.chat.mapper;

import java.util.List;

import com.spring.study.chat.model.InputMessage;
import com.spring.study.chat.model.MessagePage;
import com.spring.study.chat.model.OutputMessage;

public interface MessageMapper {

	public void insertMessage(InputMessage message);
	
	public void insertText(InputMessage message);
	
	public OutputMessage selectMessageBySeqId(int seqId);
	
	public List<OutputMessage> selectMessages(MessagePage info);
	
	public int selectTotalCountOfMessages(MessagePage info);
	
}
