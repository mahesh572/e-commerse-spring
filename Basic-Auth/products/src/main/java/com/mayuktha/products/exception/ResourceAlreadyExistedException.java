package com.mayuktha.products.exception;


public class ResourceAlreadyExistedException extends RuntimeException{
	public ResourceAlreadyExistedException(String message) {
		super(message);
	}
}
