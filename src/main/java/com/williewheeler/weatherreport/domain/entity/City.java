package com.williewheeler.weatherreport.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * OpenWeatherMap city ID.
	 */
	private Long owmCityId;

	private String name;

	public City() {
	}

	public City(Long id, Long owmCityId, String name) {
		this.id = id;
		this.owmCityId = owmCityId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwmCityId() {
		return owmCityId;
	}

	public void setOwmCityId(Long owmCityId) {
		this.owmCityId = owmCityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
