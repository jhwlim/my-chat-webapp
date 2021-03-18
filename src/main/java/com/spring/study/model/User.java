package com.spring.study.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("User")
public class User {

	private int seqId;
	private String id;
	private String name;
	private String image;

}