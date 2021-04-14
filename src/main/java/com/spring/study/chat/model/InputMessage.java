package com.spring.study.chat.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("InputMessage")
public class InputMessage {

	private int id;
	private int sender;
	private int chatRoomId;
	private String text;

}
