package com.williewheeler.weatherreport.domain.service;

import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.store.CityStore;
import com.williewheeler.weatherreport.domain.store.WeatherStore;
import lombok.Setter;

import java.util.List;

public class WeatherServiceImpl implements WeatherService {
	
	@Setter
	private CityStore cityStore;
	
	@Setter
	private WeatherStore weatherStore;
	
	@Override
	public List<WeatherReport> getWeatherReports() {
		final List<City> cities = cityStore.getAll();
		return weatherStore.getAllByCities(cities);
	}
}
