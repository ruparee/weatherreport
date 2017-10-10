package com.williewheeler.weatherreport.model;

import java.math.BigDecimal;

public class Wind {

	/** Wind speed. Unit: Default: meter/sec, Metric: meter/sec, Imperial: miles/hour */
	private BigDecimal speed;

	/** Wind direction, degrees (meteorological) */
	private int deg;

	public BigDecimal getSpeed() {
		return speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public int getDeg() {
		return deg;
	}

	public void setDeg(int deg) {
		this.deg = deg;
	}
}
