package com.md.demo.rest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.redis.util.RedisUtil;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;
import com.md.demo.vo.Person;

/**
 * @author Minbo
 */
@RestController
public class ObjectRedisTestRest {

	protected static Logger logger = LoggerFactory.getLogger(ObjectRedisTestRest.class);

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	// 例如：http://localhost:9090/setObj?id=A001&name=minbo英文名&age=100
	@RequestMapping("/setObj")
	public JsonResult setObj(String id, String name, Integer age) {
		Person person = new Person();
		person.setId(id);
		person.setName(name);
		person.setAge(age);
		logger.debug("访问set：" + person.toString());
		RedisUtil.set(this.redisTemplate, "person:" + person.getId(), person);
		return new JsonResult(ResultCode.SUCCESS);
	}

	// 例如：http://localhost:9090/getObj?id=A001
	@RequestMapping("/getObj")
	public JsonResult getObj(String id) {
		logger.debug("访问get:key id={}", id);
		Person person = (Person) RedisUtil.get(this.redisTemplate, "person:" + id);
		String result = person.toString();
		logger.debug("get obj result=" + result);
		return new JsonResult(ResultCode.SUCCESS, person);
	}
}