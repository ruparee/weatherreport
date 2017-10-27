package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.domain.entity.City;

import java.util.List;

public interface CityStore {
	
	List<City> getAll();
}
