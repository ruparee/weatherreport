package com.williewheeler.weatherreport.controller;

import com.williewheeler.weatherreport.domain.service.WeatherService;
import com.williewheeler.weatherreport.domain.template.binding.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
@CrossOrigin
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping
	public List<WeatherReport> getWeatherReports(@RequestParam(value = "resilient", defaultValue = "true") boolean resilient) {
		return weatherService.getWeatherReports(resilient);
	}
}
