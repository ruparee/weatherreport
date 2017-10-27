package com.williewheeler.weatherreport.connector.openweathermap;

import lombok.Data;

@Data
public class Sys {
	private String country;

	/** Sunrise time, Unix, UTC */
	private Long sunrise;

	/** Sunset time, Unix, UTC */
	private Long sunset;
}
