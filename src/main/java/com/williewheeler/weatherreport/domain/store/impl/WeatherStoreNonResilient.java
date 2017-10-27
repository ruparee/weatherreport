package com.williewheeler.weatherreport.domain.store.impl;

import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.owm.GistTemplate;
import com.williewheeler.weatherreport.domain.owm.OpenWeatherMapTemplate;
import com.williewheeler.weatherreport.domain.owm.binding.WeatherReport;
import com.williewheeler.weatherreport.domain.store.WeatherStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherStoreNonResilient implements WeatherStore {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherStoreNonResilient.class);
	
	@Autowired
	private OpenWeatherMapTemplate openWeatherMapTemplate;
	
	@Autowired
	private GistTemplate gistTemplate;
	
	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		try {
//			return getAllFromOpenWeatherMap(cities);
			return getAllFromGist(cities);
		} catch (RuntimeException e) {
			LOG.error("Failed to get weather reports: {}: {}", e.getClass().getName(), e.getMessage());
			throw e;
		}
	}
	
	private List<WeatherReport> getAllFromOpenWeatherMap(List<City> cities) {
		LOG.trace("Getting weather reports from OpenWeatherMap web service");
		return openWeatherMapTemplate.getWeatherReports(toOwmCityIds(cities));
	}
	
	private List<WeatherReport> getAllFromGist(List<City> cities) {
		LOG.trace("Getting weather reports from GitHub Gist web service");
		return gistTemplate.getWeatherReports(toGistUris(cities));
	}
	
	private List<Long> toOwmCityIds(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getOwmCityId())
				.collect(Collectors.toList());
	}
	
	private List<String> toGistUris(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getGistUri())
				.collect(Collectors.toList());
	}
}
