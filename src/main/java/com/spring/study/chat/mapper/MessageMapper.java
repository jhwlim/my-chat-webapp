package com.spring.study.chat.mapper;

import com.spring.study.chat.model.InputMessage;
import com.spring.study.chat.model.OutputMessage;

public interface MessageMapper {

	public void insertMessage(InputMessage message);
	
	public void insertText(InputMessage message);
	
	public OutputMessage selectMessageBySeqId(int seqId);
	
}
