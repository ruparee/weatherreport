package com.williewheeler.weatherreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Main {

	/** Temperature. Unit: Default: Kelvin, Metric: Celcius, Imperial: Fahrenheit */
	private BigDecimal temp;

	/** Atmospheric pressure (sea level, if there is no sea_level or grnd_level data), hPa */
	private int pressure;

	/** Humidity, % */
	private int humidity;

	@JsonProperty("temp_min")
	private BigDecimal tempMin;

	@JsonProperty("temp_max")
	private BigDecimal tempMax;

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public BigDecimal getTempMin() {
		return tempMin;
	}

	public void setTempMin(BigDecimal tempMin) {
		this.tempMin = tempMin;
	}

	public BigDecimal getTempMax() {
		return tempMax;
	}

	public void setTempMax(BigDecimal tempMax) {
		this.tempMax = tempMax;
	}
}
