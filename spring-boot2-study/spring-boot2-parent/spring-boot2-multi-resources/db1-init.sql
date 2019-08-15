drop table city;

CREATE TABLE `city` (
  `id` int(11) NOT NULL primary key,
  `name` varchar(32) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city` (`id`, `name`, `state`, `country`)
VALUES
	(1, '广州11', 'GZ11', 'CH11'),
	(2, '北京12', 'BJ12', 'CH12'),
	(3, '深圳13', 'SZ13', 'CH13');