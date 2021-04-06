package com.spring.study.chat.service;

import com.spring.study.chat.model.InputMessage;
import com.spring.study.chat.model.OutputMessage;

public interface MessageService {

	public OutputMessage insertMessage(InputMessage message);
	
}
