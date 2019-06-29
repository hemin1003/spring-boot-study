-- for db2

drop table city2;

CREATE TABLE `city2` (
  `id` int(11) NOT NULL primary key,
  `name` varchar(32) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city2` (`id`, `name`, `state`, `country`)
VALUES
	(1, '广州2', 'GZ2', 'CH2'),
	(2, '北京2', 'BJ2', 'CH2'),
	(3, '深圳2', 'SZ2', 'CH2');