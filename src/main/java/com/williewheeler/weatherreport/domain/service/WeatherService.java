package com.williewheeler.weatherreport.domain.service;

import com.williewheeler.weatherreport.domain.owm.binding.WeatherReport;

import java.util.List;

public interface WeatherService {

	List<WeatherReport> getWeatherReports();
}
