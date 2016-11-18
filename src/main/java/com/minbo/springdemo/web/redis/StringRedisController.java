package com.minbo.springdemo.web.redis;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot与Redis集成
 * 方式二，String
 * 
 * 参考资料：http://blog.csdn.net/lxhjh/article/details/51753852
 * 一定要放在启动boot类型package同个目录或者包含的下级目录
 * 
 * @author Minbo.He
 */
@RestController
@RequestMapping("/redis/string")
public class StringRedisController {
	protected static Logger logger = LoggerFactory.getLogger(StringRedisController.class);

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	//例如：http://localhost:8081/redis/string/set?key=address&value=guangzhou
	@RequestMapping("/set")
	public String setKeyAndValue(String key, String value) {
		logger.debug("访问set:key={},value={}", key, value);
		valOpsStr.set(key, value);
		return "Set Ok";
	}

	//例如：http://localhost:8081/redis/string/get?key=address
	@RequestMapping("/get")
	public String getKey(String key) {
		logger.debug("访问get:key={}", key);
		return valOpsStr.get(key);
	}
}
