package com.minbo.springdemo.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Rest用法
 * @author Minbo.He
 */
@Controller
public class SecurityTest {
	protected static Logger logger = LoggerFactory.getLogger(SecurityTest.class);

	@RequestMapping("/home")
	public String index() {
		return "home";
	}

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
