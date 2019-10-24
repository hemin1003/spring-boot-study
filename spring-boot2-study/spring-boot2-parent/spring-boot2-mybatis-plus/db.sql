drop table myuser;

CREATE TABLE `myuser` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `myuser` (`id`, `name`, `age`, `email`)
VALUES
	(1, 'Minbo', 18, 'test1@163.com'),
	(2, 'Hemin', 20, 'test2@163.com');