package com.williewheeler.weatherreport.domain.owm.binding;

import java.math.BigDecimal;

public class Wind {

	/** Wind speed. Unit: Default: meter/sec, Metric: meter/sec, Imperial: miles/hour */
	private BigDecimal speed;

	/** Wind direction, degrees (meteorological) */
	private Integer deg;

	public BigDecimal getSpeed() {
		return speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public Integer getDeg() {
		return deg;
	}

	public void setDeg(Integer deg) {
		this.deg = deg;
	}
}
