package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.connector.WebServiceTemplate;
import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import com.williewheeler.weatherreport.domain.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WeatherStoreGist implements WeatherStore {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		log.trace("Getting weather reports from RawGit + GitHub Gist web service");
		return webServiceTemplate.getWeatherReports(toGistUris(cities));
	}

	private List<String> toGistUris(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getGistUri())
				.collect(Collectors.toList());
	}
}
