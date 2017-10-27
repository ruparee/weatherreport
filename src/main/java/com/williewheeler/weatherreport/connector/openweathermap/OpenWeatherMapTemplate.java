package com.williewheeler.weatherreport.connector.openweathermap;

import com.williewheeler.weatherreport.connector.openweathermap.binding.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenWeatherMapTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapTemplate.class);

	private static final String WEATHER_URL_TEMPLATE =
			"https://api.openweathermap.org/data/2.5/weather?appid=%s&id=%d";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${openWeatherMap.appId}")
	private String openWeatherMapAppId;

	public List<WeatherReport> getWeatherReports(List<Long> cityIds) {
		try {
			return cityIds
					.parallelStream()
					.map(cityId -> getWeatherReport(cityId))
					.collect(Collectors.toList());
		} catch (RestClientException e) {
			throw new OpenWeatherMapException(e);
		}
	}

	private WeatherReport getWeatherReport(long cityId) {
		// TODO Replace profiling code with Coda Hale Metrics
		// Or https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring
		LOG.trace("Getting weather report: cityId={}", cityId);
		final long startTime = System.currentTimeMillis();
		final String url = String.format(WEATHER_URL_TEMPLATE, openWeatherMapAppId, cityId);
		final WeatherReport weatherReport = restTemplate.getForEntity(url, WeatherReport.class).getBody();
		final long duration = System.currentTimeMillis() - startTime;
		LOG.trace("Got weather report for cityId {} in {} ms", cityId, duration);
		return weatherReport;
	}
}
