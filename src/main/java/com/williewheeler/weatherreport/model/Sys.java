package com.williewheeler.weatherreport.model;

public class Sys {
	private String country;

	/** Sunrise time, Unix, UTC */
	private long sunrise;

	/** Sunset time, Unix, UTC */
	private long sunset;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getSunrise() {
		return sunrise;
	}

	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}

	public long getSunset() {
		return sunset;
	}

	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
}
