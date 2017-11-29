package com.williewheeler.weatherreport.domain.store;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import com.williewheeler.weatherreport.domain.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WeatherStoreResilient implements WeatherStore {
	
	@Autowired
	@Qualifier("weatherStoreS3")
	private WeatherStore weatherStoreS3;

	// Not currently using this. [WLW]
	@Autowired
	@Qualifier("weatherStoreOpenWeatherMap")
	private WeatherStore weatherStoreOpenWeatherMap;

	@Autowired
	@Qualifier("weatherStoreGist")
	private WeatherStore weatherStoreGist;
	
	@Value("${hystrix.openWeatherMap.timeout}")
	private Integer hystrixOpenWeatherMapTimeout;
	
	@Override
	public List<WeatherReport> getAllByCities(List<City> cities) {
		return new GetWeatherReportsByCities(cities).execute();
	}
	
	private class GetWeatherReportsByCities extends HystrixCommand<List<WeatherReport>> {
		private List<City> cities;
		
		public GetWeatherReportsByCities(List<City> cities) {
//			super(HystrixCommandGroupKey.Factory.asKey("OpenWeatherMapGroup"));
			super(HystrixCommand.Setter
					.withGroupKey(HystrixCommandGroupKey.Factory.asKey("OpenWeatherMapGroup"))
					.andCommandPropertiesDefaults(
							HystrixCommandProperties.Setter()
									.withExecutionTimeoutInMilliseconds(hystrixOpenWeatherMapTimeout)
					)
			);
			this.cities = cities;
		}
		
		@Override
		protected List<WeatherReport> run() throws Exception {
			return weatherStoreS3.getAllByCities(cities);
		}
		
		@Override
		protected List<WeatherReport> getFallback() {
//			return weatherStoreOpenWeatherMap.getAllByCities(cities);
			return weatherStoreGist.getAllByCities(cities);
		}
	}
}
