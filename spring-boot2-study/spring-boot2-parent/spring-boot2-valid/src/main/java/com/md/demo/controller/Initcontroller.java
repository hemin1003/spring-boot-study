package com.md.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * @author Minbo
 */
@RestController
public class Initcontroller {

	protected static Logger logger = LoggerFactory.getLogger(Initcontroller.class);

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		logger.info("hello");
		return "Hello greetings from spring-boot2-valid";
	}

	/**
	 * http://localhost:9090/exception
	 * 
	 * @return
	 */
	@ApiOperation(value = "异常测试", httpMethod = "GET")
	@GetMapping("/exception")
	public String exception() {
		int a = 10 / 0;
		return "exception，" + a;
	}
}