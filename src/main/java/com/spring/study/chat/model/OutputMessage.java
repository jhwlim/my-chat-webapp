package com.spring.study.chat.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("OutputMessage")
public class OutputMessage {

	private String sender;
	private String text;
	private Date sendDate;
	
}
