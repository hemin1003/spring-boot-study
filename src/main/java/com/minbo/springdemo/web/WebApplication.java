package com.minbo.springdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参考网址： 
 * 1. https://www.tianmaying.com/tutorial/spring-mvc-quickstart 
 * 2. https://www.tianmaying.com/tutorial/spring-boot-overview
 * 
 * @author Minbo.He
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class WebApplication {

	protected static Logger logger = LoggerFactory.getLogger(WebApplication.class);

	// 使用@Controller实现URL路由
	@RequestMapping("/index")
	public String index() {
		return "index page";
	}

	@RequestMapping("/hello")
	public String hello() {
		logger.info("HelloWorld!");
		return "HelloWorld!";
	}

	// URL中的变量——PathVariable，能够自动根据参数类型赋值
	@RequestMapping("/users/{username}")
	public String userProfile(@PathVariable("username") String username) {
		return String.format("user %s", username);
	}

	@RequestMapping("ids/{id}")
	public String post(@PathVariable("id") int id) {
		return String.format("post %d", id);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		return "Login page";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logetPost() {
		return "Login request";
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
