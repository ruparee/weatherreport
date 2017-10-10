package com.williewheeler.weatherreport.domain.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.dto.WeatherReport;
import com.williewheeler.weatherreport.domain.repo.CityRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

	private static final String WEATHER_URL_TEMPLATE =
			"https://api.openweathermap.org/data/2.5/weather?appid=%s&id=%d";

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${weatherService.key}")
	private String weatherServiceKey;

	@Override
	public List<WeatherReport> getWeatherReports() {
		List<City> cities = new GetCitiesCommand().execute();
		return new GetWeatherReportsCommand(cities).execute();
	}

	/**
	 * Hystrix command to get the list of cities.
	 */
	private class GetCitiesCommand extends HystrixCommand<List<City>> {

		public GetCitiesCommand() {
			super(HystrixCommandGroupKey.Factory.asKey("WeatherGroup"));
		}

		@Override
		protected List<City> run() throws Exception {
			LOG.trace("Getting cities");
			List<City> cities = new ArrayList<>();
			cityRepo.findAll().forEach(cities::add);
			return cities;

//			throw new RuntimeException("Simulated database exception");
		}

		@Override
		public List<City> getFallback() {
			LOG.warn("Database call failed. Falling back to hardcoded city list.");
			return Arrays.asList(
					new City(0L, 5786882L, "Bellevue")
			);
		}
	}

	/**
	 * Hystrix command to get the list of weather reports for a given list of cities.
	 */
	private class GetWeatherReportsCommand extends HystrixCommand<List<WeatherReport>> {
		private List<City> cities;

		public GetWeatherReportsCommand(List<City> cities) {
			super(HystrixCommandGroupKey.Factory.asKey("WeatherGroup"));
			this.cities = cities;
		}

		@Override
		protected List<WeatherReport> run() throws Exception {
			LOG.trace("Getting weather reports");
			try {
				return getWeatherReports();
			} catch (HttpClientErrorException e) {
				throw new WeatherReportException("Failed to get weather reports.");
			}
		}

		private List<WeatherReport> getWeatherReports() {
			return cities
					.parallelStream()
					.map(city -> getWeatherReport(city.getCode()))
					.collect(Collectors.toList());
		}

		private WeatherReport getWeatherReport(long cityId) {
			String url = String.format(WEATHER_URL_TEMPLATE, weatherServiceKey, cityId);
			HttpEntity<WeatherReport> responseEntity = restTemplate.getForEntity(url, WeatherReport.class);
			return responseEntity.getBody();
		}
	}
}
