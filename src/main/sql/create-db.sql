DROP DATABASE IF EXISTS `weatherreport`;
CREATE DATABASE `weatherreport`;

USE `weatherreport`;

CREATE TABLE `city` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `owm_city_id` INT(10) UNSIGNED NOT NULL,
  `gist_uri` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- https://stackoverflow.com/questions/27635355/how-to-create-a-gist-in-github-that-returns-json-data
-- http://rawgit.com/
INSERT INTO `city` (`name`, `owm_city_id`, `gist_uri`) VALUES
  ('Bellevue, WA, US', 5786882, 'https://cdn.rawgit.com/williewheeler/151d6c2104e9884ed112225cb3620344/raw/1b6539c530367a285ffd56f6d59754bd42fd20d6/openweathermap-bellevue.json'),
  ('Brisbane, QLD, AU', 7839562, 'https://cdn.rawgit.com/williewheeler/616a0d2bcbe183c4e9e0d548474a4af9/raw/596f193be788d88077a7568098a04a9f9e3aeb15/openweathermap-brisbane.json'),
  ('Chicago, IL, US', 4887398, 'https://cdn.rawgit.com/williewheeler/76050a45fdb3069f833d38a1ad432129/raw/cc77164e438a09281cdd546a1d14ad4a18ad53ba/openweathermap-chicago.json'),
  ('Gurgaon, HR, IN', 1270642, 'https://cdn.rawgit.com/williewheeler/3cb601420ef8d809af074e4b10bc716f/raw/8730afc74c805d8a94caee9096ae497610ec22cd/openweathermap-gurgaon.json'),
  ('London, ENG, UK', 2643743, 'https://cdn.rawgit.com/williewheeler/a62484a1346657a61b2c70a1c107d052/raw/1e6642b013d5bd245593bbf3981a2286db7c8b3a/openweathermap-london.json'),
  ('Montreal, QC, CA', 6077243, 'https://cdn.rawgit.com/williewheeler/2e705380357ca044f4c6f22ee2b840c1/raw/b1b856f51dd7e47a6dd3c85aadc5c5436ae30553/openweathermap-montreal.json'),
  ('San Francisco, CA, US', 5391959, 'https://cdn.rawgit.com/williewheeler/a5c2bf6ee11274cc56a0f8445541074b/raw/1f44e727c2731320558173fbe08489d671ecf811/openweathermap-sanfrancisco.json'),
  ('Seattle, WA, US', 5809844, 'https://cdn.rawgit.com/williewheeler/916ce283e7d3f577b2fb3eb9171e4790/raw/af843e83fc01a8a2b2b5e584a18690ddda90d823/openweathermap-seattle.json')
;
