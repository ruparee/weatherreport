package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.repo.CityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CityStoreDatabase implements CityStore {

	@Autowired
	private CityRepo cityRepo;
	
	@Override
	public List<City> getAll() {
		log.trace("Getting cities from database");
		final List<City> cities = new ArrayList<>();
		cityRepo.findAll().forEach(cities::add);
		return cities;
	}
}
