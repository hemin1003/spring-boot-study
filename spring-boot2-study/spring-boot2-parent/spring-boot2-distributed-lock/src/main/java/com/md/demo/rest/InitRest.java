package com.md.demo.rest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Minbo
 */
@RestController
public class InitRest {

	protected static Logger logger = LoggerFactory.getLogger(InitRest.class);

	@Autowired
	private RedisLockRegistry redisLockRegistry;

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		logger.info("hello");
		return "Hello greetings from spring-boot2-distributed-lock";
	}

	/**
	 * http://localhost:9090/test
	 * 
	 * @return
	 */
	@GetMapping("test")
	public String test() throws InterruptedException {
		Lock lock = this.redisLockRegistry.obtain("lock");
		boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
		logger.info("b1 is : {}", b1);

		TimeUnit.SECONDS.sleep(5);

		boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
		logger.info("b2 is : {}", b2);

		lock.unlock();
		lock.unlock();
		
		return "success";
	}
}