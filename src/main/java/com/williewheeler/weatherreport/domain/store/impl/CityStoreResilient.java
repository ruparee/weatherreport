package com.williewheeler.weatherreport.domain.store.impl;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.store.CityStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CityStoreResilient implements CityStore {
	private static final Logger LOG = LoggerFactory.getLogger(CityStoreResilient.class);
	
	@Autowired
	@Qualifier("cityStoreNonResilient" )
	private CityStore cityStore;
	
	@Override
	public List<City> getAll() {
		return new GetCitiesCommand().execute();
	}
	
	private List<City> getCitiesPrimary() {
		LOG.trace("Getting cities from database");
		try {
			return cityStore.getAll();
		} catch (RuntimeException e) {
			LOG.error("Failed to get cities from database: {}: {}", e.getClass().getName(), e.getMessage());
			throw e;
		}
	}
	
	private List<City> getCitiesFallback() {
		LOG.warn("Getting cities from fallback" );
		return Arrays.asList(
				City.builder()
						.id(0L)
						.name("Bellevue, WA, US")
						.owmCityId(5786882L)
						.gistUri("https://gist.githubusercontent.com/williewheeler/151d6c2104e9884ed112225cb3620344/raw/1b6539c530367a285ffd56f6d59754bd42fd20d6/openweathermap-bellevue.json")
						.build()
		);
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
}
