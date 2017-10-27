package com.williewheeler.weatherreport.connector;

import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebServiceTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(WebServiceTemplate.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<WeatherReport> getWeatherReports(List<String> uris) {
		try {
			return uris
					.parallelStream()
					.map(uri -> getWeatherReport(uri))
					.collect(Collectors.toList());
		} catch (RestClientException e) {
			throw new ConnectorException(e);
		}
	}
	
	private WeatherReport getWeatherReport(String uri) {
		// TODO Replace profiling code with Coda Hale Metrics
		// Or https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring
		LOG.trace("Getting weather report: uri={}", uri);
		final long startTime = System.currentTimeMillis();
		final WeatherReport weatherReport = restTemplate.getForEntity(uri, WeatherReport.class).getBody();
		final long duration = System.currentTimeMillis() - startTime;
		LOG.trace("Got weather report for uri {} in {} ms", uri, duration);
		return weatherReport;
	}
}
