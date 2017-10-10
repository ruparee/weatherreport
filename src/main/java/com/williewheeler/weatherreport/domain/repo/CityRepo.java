package com.williewheeler.weatherreport.domain.repo;

import com.williewheeler.weatherreport.domain.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends CrudRepository<City, Long> {
}
