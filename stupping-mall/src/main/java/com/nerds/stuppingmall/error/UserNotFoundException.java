package com.nerds.stuppingmall.error;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String id) {
		super(id + " UserNotFoundException");
	}
}
