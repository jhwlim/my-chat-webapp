package com.spring.study.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.spring.study.chat.mapper.MessageMapper;
import com.spring.study.chat.model.InputMessage;
import com.spring.study.chat.model.MessagePage;
import com.spring.study.chat.model.OutputMessage;
import com.spring.study.chat.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageMapper mapper;
	
	private final PlatformTransactionManager transactionManager;
	
	public MessageServiceImpl(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	
	@Override
	public OutputMessage insertMessage(InputMessage message) {

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
	
		try {
			mapper.insertMessage(message);
			mapper.insertText(message);
			
			transactionManager.commit(txStatus);
			return mapper.selectMessageBySeqId(message.getSeqId());
		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return null;
	}

	@Override
	public List<OutputMessage> selectMessages(MessagePage info) {
		return mapper.selectMessages(info);
	}


	@Override
	public int selectTotalCountOfMessages(MessagePage info) {
		return mapper.selectTotalCountOfMessages(info);
	}

	
}
