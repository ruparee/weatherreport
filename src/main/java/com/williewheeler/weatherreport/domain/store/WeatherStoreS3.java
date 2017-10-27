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
public class WeatherStoreS3 implements WeatherStore {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherStoreS3.class);

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		LOG.trace("Getting weather reports from S3 web service");
		return webServiceTemplate.getWeatherReports(toS3Uris(cities));
	}

	private List<String> toS3Uris(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getS3Uri())
				.collect(Collectors.toList());
	}
}
