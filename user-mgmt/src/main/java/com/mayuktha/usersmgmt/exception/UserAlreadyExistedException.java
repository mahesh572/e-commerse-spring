package com.mayuktha.usersmgmt.exception;


public class UserAlreadyExistedException extends RuntimeException{
	public UserAlreadyExistedException(String message) {
		super(message);
	}
}
