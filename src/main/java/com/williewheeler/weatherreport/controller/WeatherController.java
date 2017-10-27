package com.williewheeler.weatherreport.controller;

import com.williewheeler.weatherreport.domain.service.WeatherService;
import com.williewheeler.weatherreport.domain.owm.binding.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
public class WeatherController {
	
	@Autowired
	@Qualifier("weatherServiceResilient")
	private WeatherService weatherServiceResilient;
	
	@Autowired
	@Qualifier("weatherServiceNonResilient")
	private WeatherService weatherServiceNonResilient;

	@RequestMapping(method = RequestMethod.GET)
	public List<WeatherReport> getWeatherReports(@RequestParam(value = "resilient", defaultValue = "true") boolean resilient) {
		return resilient ?
				weatherServiceResilient.getWeatherReports() :
				weatherServiceNonResilient.getWeatherReports();
	}
}
