package com.williewheeler.weatherreport.domain.service;

import com.williewheeler.weatherreport.domain.dto.WeatherReport;

import java.util.List;

public interface WeatherService {

	List<WeatherReport> getWeatherReports();
}
