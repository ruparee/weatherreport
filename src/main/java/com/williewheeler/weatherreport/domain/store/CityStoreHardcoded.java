package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.domain.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CityStoreHardcoded implements CityStore {

	@Override
	public List<City> getAll() {
		log.trace("Getting hardcoded cities");
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
