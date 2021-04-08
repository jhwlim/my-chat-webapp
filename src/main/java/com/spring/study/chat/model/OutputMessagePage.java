package com.spring.study.chat.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class OutputMessagePage {
	
	@Setter
	private List<OutputMessage> messages;
	private boolean next;
	
	public void setNext(int pageStart, int pageCount, int totalCount) {
		this.next = pageStart + pageCount < totalCount;
	}
	
}
