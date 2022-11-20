package com.example.TPtrace.exception;

public class NoUserWithId extends RuntimeException {
	
	private String message;

	public NoUserWithId(String message) {
		super(message);
		this.message = message;
	}
	
}
