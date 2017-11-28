package com.williewheeler.weatherreport.domain.store;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.williewheeler.weatherreport.domain.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityStoreResilient implements CityStore {

	@Autowired
	@Qualifier("cityStoreDatabase")
	private CityStore cityStoreDatabase;

	@Autowired
	@Qualifier("cityStoreHardcoded")
	private CityStore cityStoreHardcoded;
	
	@Value("${hystrix.database.timeout}")
	private Integer hystrixDatabaseTimeout;

	@Override
	public List<City> getAll() {
		return new GetCitiesCommand().execute();
	}

	private class GetCitiesCommand extends HystrixCommand<List<City>> {
		
		public GetCitiesCommand() {
//			super(HystrixCommandGroupKey.Factory.asKey("DatabaseGroup"));
			super(HystrixCommand.Setter
					.withGroupKey(HystrixCommandGroupKey.Factory.asKey("DatabaseGroup"))
					.andCommandPropertiesDefaults(
							HystrixCommandProperties.Setter()
									.withExecutionTimeoutInMilliseconds(hystrixDatabaseTimeout)
					)
			);
		}
		
		@Override
		protected List<City> run() throws Exception {
			return cityStoreDatabase.getAll();
		}
		
		@Override
		public List<City> getFallback() {
			log.warn("Unable to get cities from primary city store. Falling back.");
			return cityStoreHardcoded.getAll();
		}
	}
}
