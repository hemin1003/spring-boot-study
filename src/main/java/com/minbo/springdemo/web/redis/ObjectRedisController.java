package com.minbo.springdemo.web.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot与Redis集成
 * 方式一，Object
 * @author Minbo.He
 */
@RestController
@RequestMapping("/redis/object")
public class ObjectRedisController {
	
	protected static Logger logger = LoggerFactory.getLogger(ObjectRedisController.class);

	@Autowired
	PersonDao personDao;

	//例如：http://localhost:8081/redis/object/setPerson?id=1&name=zhangsan&age=20
	@RequestMapping("/setPerson")
	public void set(String id, String name, Integer age) {
		logger.debug("访问setPerson:id={},name={},age={}", id, name, age);
		Person person = new Person(id, name, age);
		personDao.save(person);
	}

	//例如：http://localhost:8081/redis/object/getPerson?id=1
	@RequestMapping("/getPerson")
	public Person getPerson(String id) {
		return personDao.getPerson(id);
	}
}
