package com.williewheeler.weatherreport.domain.service;

@SuppressWarnings("serial")
public class WeatherServiceException extends RuntimeException {

	public WeatherServiceException(String message) {
		super(message);
	}

	public WeatherServiceException(Throwable cause) {
		super(cause);
	}
}
