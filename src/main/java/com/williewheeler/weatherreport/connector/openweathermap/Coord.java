package com.williewheeler.weatherreport.connector.openweathermap;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coord {
	private BigDecimal lon;
	private BigDecimal lat;
}
