package com.williewheeler.weatherreport.connector.openweathermap;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wind {

	/** Wind speed. Unit: Default: meter/sec, Metric: meter/sec, Imperial: miles/hour */
	private BigDecimal speed;

	/** Wind direction, degrees (meteorological) */
	private Integer deg;
}
