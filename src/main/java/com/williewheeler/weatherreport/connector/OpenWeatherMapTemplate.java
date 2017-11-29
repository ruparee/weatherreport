package com.williewheeler.weatherreport.connector;

import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OpenWeatherMapTemplate {

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
			throw new ConnectorException(e);
		}
	}

	private WeatherReport getWeatherReport(long cityId) {
		// TODO Replace profiling code with Coda Hale Metrics
		// Or https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring
		log.trace("Getting weather report: cityId={}", cityId);
		final long startTime = System.currentTimeMillis();
		final String url = String.format(WEATHER_URL_TEMPLATE, openWeatherMapAppId, cityId);
		final WeatherReport weatherReport = restTemplate.getForEntity(url, WeatherReport.class).getBody();
		final long duration = System.currentTimeMillis() - startTime;
		log.trace("Got weather report for cityId {} in {} ms", cityId, duration);
		return weatherReport;
	}
}
