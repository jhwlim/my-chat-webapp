package com.spring.study.chat.model;

import lombok.Data;

@Data
public class Message {

	private int toSeqId;
	private String to;
	private String text;
	
}
