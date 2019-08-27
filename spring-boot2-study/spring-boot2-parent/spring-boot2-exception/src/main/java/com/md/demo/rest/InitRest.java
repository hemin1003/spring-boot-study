package com.md.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;

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
		return "Hello greetings from spring-boot2-exception";
	}

	/**
	 * http://localhost:9090/exception
	 * 
	 * @return
	 */
	@GetMapping("/exception")
	public String exception() {
		int a = 10 / 0;
		return "exception，" + a;
	}

	@GetMapping("/error")
	public JsonResult error() {
		return new JsonResult(ResultCode.SUCCESS_FAIL, "error错误");
	}
}