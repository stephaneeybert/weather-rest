package com.thalasoft.weatherrest.exception;

@SuppressWarnings("serial")
public class InvalidAccessException extends EnrichableException {

	public InvalidAccessException(String accessKey) {
		super("The access key " + accessKey + " was invalid.");
	}

}