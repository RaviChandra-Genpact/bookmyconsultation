package com.upgrad.bookmyconsultation.exception;

public class ResourceUnAvailableException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public ResourceUnAvailableException(String message) {
        super(message);
    }
}
