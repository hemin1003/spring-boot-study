package com.md.demo.rest;

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
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		return "Hello greetings from spring-boot2-dockerfile";
	}
}