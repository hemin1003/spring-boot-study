-- for db1

drop table city1;

CREATE TABLE `city1` (
  `id` int(11) NOT NULL primary key,
  `name` varchar(32) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city1` (`id`, `name`, `state`, `country`)
VALUES
	(1, '广州1', 'GZ1', 'CH1'),
	(2, '北京1', 'BJ1', 'CH1'),
	(3, '深圳1', 'SZ1', 'CH1');