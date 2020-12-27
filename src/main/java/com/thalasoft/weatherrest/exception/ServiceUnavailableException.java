package com.thalasoft.weatherrest.exception;

@SuppressWarnings("serial")
public class ServiceUnavailableException extends EnrichableException {

  public ServiceUnavailableException() {
    super("The service was not available.");
  }

}