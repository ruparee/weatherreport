package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.connector.WebServiceTemplate;
import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import com.williewheeler.weatherreport.domain.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherStoreGist implements WeatherStore {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherStoreGist.class);

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		LOG.trace("Getting weather reports from RawGit + GitHub Gist web service");
		return webServiceTemplate.getWeatherReports(toGistUris(cities));
	}

	private List<String> toGistUris(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getGistUri())
				.collect(Collectors.toList());
	}
}
