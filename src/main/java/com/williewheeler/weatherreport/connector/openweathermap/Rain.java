package com.williewheeler.weatherreport.connector.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rain {

	/** Rain volume for the last 3 hours */
	@JsonProperty("3h")
	private Integer lastThreeHours;
}
