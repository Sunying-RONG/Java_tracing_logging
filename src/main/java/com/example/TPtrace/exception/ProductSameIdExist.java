package com.example.TPtrace.exception;

public class ProductSameIdExist extends RuntimeException {
	
	private String message;

	public ProductSameIdExist(String message) {
		super(message);
		this.message = message;
	}
	
	

}
