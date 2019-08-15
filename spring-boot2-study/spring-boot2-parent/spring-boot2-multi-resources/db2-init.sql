drop table city;

CREATE TABLE `city` (
  `id` int(11) NOT NULL primary key,
  `name` varchar(32) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city` (`id`, `name`, `state`, `country`)
VALUES
	(4, '广州14', 'GZ14', 'CH14'),
	(5, '北京15', 'BJ15', 'CH15'),
	(6, '深圳16', 'SZ16', 'CH16');