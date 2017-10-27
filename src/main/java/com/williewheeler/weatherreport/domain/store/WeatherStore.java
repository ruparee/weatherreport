package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.owm.binding.WeatherReport;

import java.util.List;

public interface WeatherStore {
	
	List<WeatherReport> getAllByCities(List<City> cities);
}
