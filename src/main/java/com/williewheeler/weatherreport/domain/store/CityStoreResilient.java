package com.williewheeler.weatherreport.domain.store;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.store.CityStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityStoreResilient implements CityStore {

	@Autowired
	@Qualifier("cityStoreDatabase")
	private CityStore cityStoreDatabase;

	@Autowired
	@Qualifier("cityStoreHardcoded")
	private CityStore cityStoreHardcoded;

	@Override
	public List<City> getAll() {
		return new GetCitiesCommand().execute();
	}

	private class GetCitiesCommand extends HystrixCommand<List<City>> {
		
		public GetCitiesCommand() {
			super(HystrixCommandGroupKey.Factory.asKey("DatabaseGroup"));
		}
		
		@Override
		protected List<City> run() throws Exception {
			return cityStoreDatabase.getAll();
		}
		
		@Override
		public List<City> getFallback() {
			return cityStoreHardcoded.getAll();
		}
	}
}
