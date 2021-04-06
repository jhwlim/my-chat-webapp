package com.spring.study.chat.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("InputMessage")
public class InputMessage {

	private int seqId;
	private int sender;
	private int receiver;
	private String receiverId;
	private String text;

}
