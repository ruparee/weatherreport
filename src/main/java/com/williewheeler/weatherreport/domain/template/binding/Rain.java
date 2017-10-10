package com.williewheeler.weatherreport.domain.template.binding;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {

	/** Rain volume for the last 3 hours */
	@JsonProperty("3h")
	private Integer lastThreeHours;

	public Integer getLastThreeHours() {
		return lastThreeHours;
	}

	public void setLastThreeHours(Integer lastThreeHours) {
		this.lastThreeHours = lastThreeHours;
	}
}
