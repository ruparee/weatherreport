package com.williewheeler.weatherreport.controller;

import com.williewheeler.weatherreport.connector.openweathermap.binding.WeatherReport;
import com.williewheeler.weatherreport.domain.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
@CrossOrigin
public class WeatherController {
	
	@Autowired
	@Qualifier("weatherServiceResilient")
	private WeatherService weatherServiceResilient;
	
	@Autowired
	@Qualifier("weatherServiceNonResilient")
	private WeatherService weatherServiceNonResilient;

	@GetMapping
	public List<WeatherReport> getWeatherReports(@RequestParam(value = "resilient", defaultValue = "true") boolean resilient) {
		return resilient ?
				weatherServiceResilient.getWeatherReports() :
				weatherServiceNonResilient.getWeatherReports();
	}
}
