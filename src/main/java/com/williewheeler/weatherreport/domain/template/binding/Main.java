package com.williewheeler.weatherreport.domain.template.binding;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

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

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	public Integer getPressure() {
		return pressure;
	}

	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
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

	public Integer getSeaLevel() {
		return seaLevel;
	}

	public void setSeaLevel(Integer seaLevel) {
		this.seaLevel = seaLevel;
	}

	public Integer getGroundLevel() {
		return groundLevel;
	}

	public void setGroundLevel(Integer groundLevel) {
		this.groundLevel = groundLevel;
	}
}
