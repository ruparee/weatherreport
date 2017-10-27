package com.williewheeler.weatherreport.connector.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Main {

	/** Temperature. Unit: Default: Kelvin, Metric: Celcius, Imperial: Fahrenheit */
	private BigDecimal temp;

	/** Atmospheric pressure (sea level, if there is no sea_level or grnd_level data), hPa */
	private Integer pressure;

	/** Humidity, % */
	private Integer humidity;

	@JsonProperty("temp_min")
	private BigDecimal tempMin;

	@JsonProperty("temp_max")
	private BigDecimal tempMax;

	/** Atmospheric pressure at sea level, hPa */
	@JsonProperty("sea_level")
	private Integer seaLevel;

	/** Atmospheric pressure at ground level, hPa */
	@JsonProperty("grnd_level")
	private Integer groundLevel;
}
