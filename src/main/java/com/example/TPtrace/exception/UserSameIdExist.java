package com.example.TPtrace.exception;

public class UserSameIdExist extends RuntimeException {
	
	private String message;

	public UserSameIdExist(String message) {
		super(message);
		this.message = message;
	}

}
