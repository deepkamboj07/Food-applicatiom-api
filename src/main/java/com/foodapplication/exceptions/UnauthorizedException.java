package com.foodapplication.exceptions;

public class UnauthorizedException extends RuntimeException {

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
