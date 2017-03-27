package com.minbo.springdemo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest用法
 * @author Minbo.He
 */
@RestController
@RequestMapping("/rest")
public class ControllerTest {
	protected static Logger logger = LoggerFactory.getLogger(ControllerTest.class);

	// 使用@Controller实现URL路由
	@RequestMapping("/hello")
	public String greeting() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/index")
	public String index() {
		return "index page";
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
}
