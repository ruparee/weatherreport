package com.williewheeler.weatherreport.domain.store;

import com.williewheeler.weatherreport.domain.entity.City;
import com.williewheeler.weatherreport.domain.repo.CityRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityStoreDatabase implements CityStore {
	private static final Logger LOG = LoggerFactory.getLogger(CityStoreResilient.class);
	
	@Autowired
	private CityRepo cityRepo;
	
	@Override
	public List<City> getAll() {
		final List<City> cities = new ArrayList<>();
		cityRepo.findAll().forEach(cities::add);
		return cities;
	}
}
