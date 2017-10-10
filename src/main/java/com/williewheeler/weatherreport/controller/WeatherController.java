package com.williewheeler.weatherreport.controller;

import com.williewheeler.weatherreport.domain.dto.WeatherReport;
import com.williewheeler.weatherreport.domain.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(method = RequestMethod.GET)
	public List<WeatherReport> getWeatherReports() {
		return weatherService.getWeatherReports();
	}
}
