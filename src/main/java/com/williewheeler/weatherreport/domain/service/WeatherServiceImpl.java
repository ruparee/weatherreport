package com.williewheeler.weatherreport.domain.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.repo.CityRepo;
import com.williewheeler.weatherreport.domain.template.OpenWeatherMapTemplate;
import com.williewheeler.weatherreport.domain.template.binding.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private OpenWeatherMapTemplate openWeatherMapTemplate;

	@Value("${hystrix.openWeatherMap.timeout}")
	private Integer hystrixOpenWeatherMapTimeout;

	@Value("${resilienceMode}")
	private Boolean resilienceMode;

	@Override
	public List<WeatherReport> getWeatherReports() {
		return resilienceMode ? getWeatherReportsProtected() : getWeatherReportsUnprotected();
	}

	private List<WeatherReport> getWeatherReportsUnprotected() {
		final List<City> cities = getCitiesPrimary();
		return getWeatherReportsByCityIds(toCityIds(cities));
	}

	private List<WeatherReport> getWeatherReportsProtected() {
		final List<City> cities = new GetCitiesCommand().execute();
		return new GetWeatherReportsByCityIds(toCityIds(cities)).execute();
	}

	private List<City> getCitiesPrimary() {
		LOG.trace("Getting cities from database");
		final List<City> cities = new ArrayList<>();
		cityRepo.findAll().forEach(cities::add);
		return cities;
//		throw new RuntimeException("Simulated database exception");
	}

	private List<City> getCitiesFallback() {
		LOG.warn("Failed to get cities from database. Falling back to hardcoded cities.");
		return Arrays.asList(
				new City(0L, 5786882L, "Bellevue")
		);
	}

	private List<Long> toCityIds(List<City> cities) {
		return cities
				.stream()
				.map(city -> city.getOwmCityId())
				.collect(Collectors.toList());
	}

	private List<WeatherReport> getWeatherReportsByCityIds(List<Long> cityIds) {
		LOG.trace("Getting weather reports from OpenWeatherMap web service");
		return openWeatherMapTemplate.getWeatherReports(cityIds);
	}

	private class GetCitiesCommand extends HystrixCommand<List<City>> {

		public GetCitiesCommand() {
			super(HystrixCommandGroupKey.Factory.asKey("DatabaseGroup"));
		}

		@Override
		protected List<City> run() throws Exception {
			return getCitiesPrimary();
		}

		@Override
		public List<City> getFallback() {
			return getCitiesFallback();
		}
	}

	private class GetWeatherReportsByCityIds extends HystrixCommand<List<WeatherReport>> {
		private List<Long> cityIds;

		public GetWeatherReportsByCityIds(List<Long> cityIds) {
//			super(HystrixCommandGroupKey.Factory.asKey("OpenWeatherMapGroup"));
			super(Setter
					.withGroupKey(HystrixCommandGroupKey.Factory.asKey("OpenWeatherMapGroup"))
					.andCommandPropertiesDefaults(
							HystrixCommandProperties.Setter()
									.withExecutionTimeoutInMilliseconds(hystrixOpenWeatherMapTimeout)
					)
			);
			this.cityIds = cityIds;
		}

		@Override
		protected List<WeatherReport> run() throws Exception {
			return getWeatherReportsByCityIds(cityIds);
		}
	}
}
