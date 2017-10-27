package com.williewheeler.weatherreport.domain.store.impl;

import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.connector.WebServiceTemplate;
import com.williewheeler.weatherreport.connector.OpenWeatherMapTemplate;
import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
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
	private WebServiceTemplate webServiceTemplate;
	
	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		try {
//			return getAllFromOpenWeatherMap(cities);
			return getAllFromS3(cities);
//			return getAllFromGist(cities);
		} catch (RuntimeException e) {
			LOG.error("Failed to get weather reports: {}: {}", e.getClass().getName(), e.getMessage());
			throw e;
		}
	}

	// This one is rate-limited
	private List<WeatherReport> getAllFromOpenWeatherMap(List<City> cities) {
		LOG.trace("Getting weather reports from OpenWeatherMap web service");
		return openWeatherMapTemplate.getWeatherReports(toOwmCityIds(cities));
	}

	// This is probably the most reliable as it's redundantly-stored JSON files in S3.
	private List<WeatherReport> getAllFromS3(List<City> cities) {
		LOG.trace("Getting weather reports from S3 web service");
		return webServiceTemplate.getWeatherReports(toS3Uris(cities));
	}

	// This goes through a third-party proxy with no SLA. It's supposed to be fast but no availability guarantee.
	private List<WeatherReport> getAllFromGist(List<City> cities) {
		LOG.trace("Getting weather reports from RawGit + GitHub Gist web service");
		return webServiceTemplate.getWeatherReports(toGistUris(cities));
	}
	
	private List<Long> toOwmCityIds(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getOwmCityId())
				.collect(Collectors.toList());
	}

	private List<String> toS3Uris(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getS3Uri())
				.collect(Collectors.toList());
	}

	private List<String> toGistUris(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getGistUri())
				.collect(Collectors.toList());
	}
}
