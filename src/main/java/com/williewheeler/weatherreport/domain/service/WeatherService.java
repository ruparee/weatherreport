package com.williewheeler.weatherreport.domain.service;

import com.williewheeler.weatherreport.domain.template.binding.WeatherReport;

import java.util.List;

public interface WeatherService {

	List<WeatherReport> getWeatherReports(boolean resilient);
}
