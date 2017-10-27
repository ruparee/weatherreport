package com.williewheeler.weatherreport.connector.openweathermap;

import lombok.Data;

@Data
public class Weather {
	private Long id;
	private String main;
	private String description;
	private String icon;
}
