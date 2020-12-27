package com.thalasoft.weatherrest.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends EnrichableException {

	public ResourceNotFoundException(String city) {
		super("The resource of the city " + city + " was not found.");
	}

}