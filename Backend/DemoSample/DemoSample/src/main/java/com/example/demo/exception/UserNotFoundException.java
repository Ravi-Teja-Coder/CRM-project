package com.example.demo.exception;

public class UserNotFoundException extends Exception {

	private String msg;
	public UserNotFoundException(String msg)
	{
		this.msg=msg;
		
	}
}
