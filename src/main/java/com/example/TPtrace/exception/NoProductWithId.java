package com.example.TPtrace.exception;

public class NoProductWithId extends RuntimeException {
	
	private String message;

	public NoProductWithId(String message) {
		super(message);
		this.message = message;
	}
	
}
