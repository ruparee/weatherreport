package com.williewheeler.weatherreport.domain.store;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.williewheeler.weatherreport.connector.openweathermap.WeatherReport;
import com.williewheeler.weatherreport.domain.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStoreResilient implements WeatherStore {
	
	@Autowired
	@Qualifier("weatherStoreS3")
	private WeatherStore weatherStoreS3;

	@Autowired
	@Qualifier("weatherStoreOpenWeatherMap")
	private WeatherStore weatherStoreOpenWeatherMap;

	// Not currently using this. [WLW]
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
		
		// For now we won't fallback here, because we want to attack port 443,
		// and having this fallback will probably cause the OpenWeatherMap call
		// to hang. [WLW]
//		@Override
//		protected List<WeatherReport> getFallback() {
//			return weatherStoreOpenWeatherMap.getAllByCities(cities);
//		}
	}
}
