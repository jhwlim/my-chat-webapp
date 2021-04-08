package com.spring.study.chat.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Alias("OutputMessage")
public class OutputMessage {

	private int sender;
	@Setter
	private String senderId;
	private String text;
	private String sendDate;
	private String sendDateTime;
	
	public void setSendDate(Date sendDate) {
		DateFormat dateFormat = new SimpleDateFormat("YYYY년 M월 d일 E요일");		
		this.sendDate = dateFormat.format(sendDate);
		
		setSendDateTime(sendDate);
	}
	
	private void setSendDateTime(Date sendDate) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		this.sendDateTime = dateFormat.format(sendDate);
	}
	
}
