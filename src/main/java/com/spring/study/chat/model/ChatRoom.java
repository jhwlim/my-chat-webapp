package com.spring.study.chat.model;

import lombok.Getter;

@Getter
public class ChatRoom {
	
	public static final int ONE_TO_ONE = 0;
	public static final int GROUP = 1;
	
	private int id;
	private int type;
	
}
