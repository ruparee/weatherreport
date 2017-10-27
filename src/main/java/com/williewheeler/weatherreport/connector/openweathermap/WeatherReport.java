package com.williewheeler.weatherreport.connector.openweathermap;

import lombok.Data;

import java.util.List;

/**
 * See https://openweathermap.org/current#current_JSON
 */
@Data
public class WeatherReport {

	// Commenting these out to simplify the response for our workshop. [WLW]

//	private Coord coord;
	private List<Weather> weather;
//	private Main main;
//	private Integer visibility;
//	private Wind wind;
//	private Clouds clouds;
//	private Rain rain;
//	private Snow snow;

	/** Time of data calculation, Unix, UTC */
//	private Long dt;

//	private Sys sys;
//	private Long id;
	private String name;
}
