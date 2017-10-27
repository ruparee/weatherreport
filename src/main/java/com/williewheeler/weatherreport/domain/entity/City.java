package com.williewheeler.weatherreport.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	
	/**
	 * OpenWeatherMap city ID.
	 */
	private Long owmCityId;

	// Note that this explicit column name is required.
	// Otherwise the mapping fails. Not sure what the mapper is trying to map to. :)
	@Column(name = "s3_uri")
	private String s3Uri;

	private String gistUri;
}
