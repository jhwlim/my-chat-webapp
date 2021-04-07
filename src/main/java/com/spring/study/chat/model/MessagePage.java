package com.spring.study.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessagePage {

	private int sender;
	private int receiver;
	
	private int pageIndex;
	private final int pageCount = 10;
	
}
