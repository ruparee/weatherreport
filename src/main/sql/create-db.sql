DROP DATABASE IF EXISTS `weatherreport`;
CREATE DATABASE `weatherreport`;

USE `weatherreport`;

CREATE TABLE `city` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `owm_city_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`owm_city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city` (`owm_city_id`, `name`) VALUES
  (5786882, 'Bellevue, WA, US'),
  (7839562, 'Brisbane, QLD, AU'),
  (4887398, 'Chicago, IL, US'),
  (1270642, 'Gurgaon, HR, IN'),
  (2643743, 'London, ENG, UK'),
  (6077243, 'Montreal, QC, CA'),
  (5391959, 'San Francisco, CA, US'),
  (5809844, 'Seattle, WA, US')
;
