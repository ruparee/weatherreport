package com.williewheeler.weatherreport.connector.gist;

import com.williewheeler.weatherreport.connector.openweathermap.OpenWeatherMapException;
import com.williewheeler.weatherreport.connector.openweathermap.binding.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GistTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(GistTemplate.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<WeatherReport> getWeatherReports(List<String> gistUris) {
		try {
			return gistUris
					.parallelStream()
					.map(gistUri -> getWeatherReport(gistUri))
					.collect(Collectors.toList());
		} catch (RestClientException e) {
			throw new OpenWeatherMapException(e);
		}
	}
	
	private WeatherReport getWeatherReport(String gistUri) {
		// TODO Replace profiling code with Coda Hale Metrics
		// Or https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring
		LOG.trace("Getting weather report: gistUri={}", gistUri);
		final long startTime = System.currentTimeMillis();
		final WeatherReport weatherReport = restTemplate.getForEntity(gistUri, WeatherReport.class).getBody();
		final long duration = System.currentTimeMillis() - startTime;
		LOG.trace("Got weather report for gistUri {} in {} ms", gistUri, duration);
		return weatherReport;
	}
}
