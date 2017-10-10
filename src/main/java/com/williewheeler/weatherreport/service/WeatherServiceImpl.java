package com.williewheeler.weatherreport.service;

import com.williewheeler.weatherreport.model.City;
import com.williewheeler.weatherreport.model.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

	private static final String WEATHER_URL_TEMPLATE =
			"https://api.openweathermap.org/data/2.5/weather?appid=%s&id=%d";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${weatherService.key}")
	private String weatherServiceKey;

	@Override
	public List<WeatherReport> getWeatherReports() {
		LOG.info("Getting weather reports");
		return getCities()
				.parallelStream()
				.map(city -> getWeatherReport(city.getId()))
				.collect(Collectors.toList());
	}

	private List<City> getCities() {
		return Arrays.asList(
				new City(5786882L, "Bellevue"),
				new City(7839562L, "Brisbane"),
				new City(4887398L, "Chicago"),
				new City(1270642L, "Gurgaon"),
				new City(2643743L, "London"),
				new City(6077243L, "Montreal"),
				new City(5391959L, "San Francisco"),
				new City(5809844L, "Seattle")
		);
	}

	private WeatherReport getWeatherReport(long cityId) {
		String url = String.format(WEATHER_URL_TEMPLATE, weatherServiceKey, cityId);
		HttpEntity<WeatherReport> responseEntity = restTemplate.getForEntity(url, WeatherReport.class);
		return responseEntity.getBody();
	}
}
