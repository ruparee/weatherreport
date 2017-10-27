package com.williewheeler.weatherreport.domain.service;

import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;

import java.util.List;

public interface WeatherService {

	List<WeatherReport> getWeatherReports();
}
