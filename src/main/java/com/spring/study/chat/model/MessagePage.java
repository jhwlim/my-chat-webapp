package com.spring.study.chat.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessagePage {

	private int sender;
	private int chatRoomId;
	
	@Setter(AccessLevel.NONE)
	private int pageIndex;
	private final int pageCount = 10;
	private int pageStart;
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		this.pageStart = pageIndex * pageCount;
	}
}
