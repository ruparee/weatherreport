package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.connector.OpenWeatherMapTemplate;
import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import com.williewheeler.weatherreport.domain.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Fresh data but rate-limited.
 */
@Service
@Slf4j
public class WeatherStoreOpenWeatherMap implements WeatherStore {

	@Autowired
	private OpenWeatherMapTemplate openWeatherMapTemplate;

	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		log.trace("Getting weather reports from OpenWeatherMap web service");
		return openWeatherMapTemplate.getWeatherReports(toOwmCityIds(cities));
	}

	private List<Long> toOwmCityIds(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getOwmCityId())
				.collect(Collectors.toList());
	}
}
