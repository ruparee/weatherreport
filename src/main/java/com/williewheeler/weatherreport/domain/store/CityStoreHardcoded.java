package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.domain.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CityStoreHardcoded implements CityStore {
	private static final Logger LOG = LoggerFactory.getLogger(CityStoreHardcoded.class);

	@Override
	public List<City> getAll() {
		LOG.trace("Getting hardcoded cities");
		return Arrays.asList(
				City.builder()
						.id(0L)
						.name("Bellevue, WA, US")
						.owmCityId(5786882L)
						.gistUri("https://cdn.rawgit.com/williewheeler/151d6c2104e9884ed112225cb3620344/raw/1b6539c530367a285ffd56f6d59754bd42fd20d6/openweathermap-bellevue.json")
						.s3Uri("https://s3.amazonaws.com/willie/public/weatherreport/openweathermap-bellevue.json")
						.build()
		);
	}
}
