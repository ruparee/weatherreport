package com.williewheeler.weatherreport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {

	/** Rain volume for the last 3 hours */
	@JsonProperty("3h")
	private int lastThreeHours;

	public int getLastThreeHours() {
		return lastThreeHours;
	}

	public void setLastThreeHours(int lastThreeHours) {
		this.lastThreeHours = lastThreeHours;
	}
}
