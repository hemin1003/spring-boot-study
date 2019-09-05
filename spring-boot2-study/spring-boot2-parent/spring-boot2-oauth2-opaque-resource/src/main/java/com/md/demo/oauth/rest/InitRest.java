package com.md.demo.oauth.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Minbo
 */
@RestController
public class InitRest {

	protected static Logger logger = LoggerFactory.getLogger(InitRest.class);

	/**
	 * http://localhost:9101/hello
	 * 
	 * 在ResourceServerConfiguration.java文件中，开启了忽略放行，不验证token
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		logger.info("hello");
		return "Hello greetings from spring-boot2-oauth2-opaque-resource";
	}
}