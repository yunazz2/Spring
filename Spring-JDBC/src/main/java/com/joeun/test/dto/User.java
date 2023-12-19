package com.joeun.test.dto;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String name;
	private String email;
	private Date regDate;
	private Date updDate;
	
	
	public User() {
		
	}

	public User(int userNo, String userId, String userPw, String name, String email, Date regDate, Date updDate) {
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.email = email;
		this.regDate = regDate;
		this.updDate = updDate;
	}
	
	
	
}
