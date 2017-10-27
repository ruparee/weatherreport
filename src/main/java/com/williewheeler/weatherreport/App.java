package com.williewheeler.weatherreport;

import com.williewheeler.weatherreport.domain.service.WeatherService;
import com.williewheeler.weatherreport.domain.service.impl.WeatherServiceImpl;
import com.williewheeler.weatherreport.domain.store.CityStore;
import com.williewheeler.weatherreport.domain.store.WeatherStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {
	
	@Autowired
	@Qualifier("cityStoreResilient")
	private CityStore cityStoreResilient;
	
	@Autowired
	@Qualifier("cityStoreNonResilient")
	private CityStore cityStoreNonResilient;
	
	@Autowired
	@Qualifier("weatherStoreResilient")
	private WeatherStore weatherStoreResilient;
	
	@Autowired
	@Qualifier("weatherStoreNonResilient")
	private WeatherStore weatherStoreNonResilient;
	
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
	
    @Bean
	public WeatherService weatherServiceResilient() {
    	final WeatherServiceImpl weatherService = new WeatherServiceImpl();
    	weatherService.setCityStore(cityStoreResilient);
    	weatherService.setWeatherStore(weatherStoreResilient);
    	return weatherService;
	}
	
	@Bean
	public WeatherService weatherServiceNonResilient() {
		final WeatherServiceImpl weatherService = new WeatherServiceImpl();
		weatherService.setCityStore(cityStoreNonResilient);
		weatherService.setWeatherStore(weatherStoreNonResilient);
		return weatherService;
	}
	
    @Bean
	public RestTemplate restTemplate() {
    	return new RestTemplate();
	}
}
