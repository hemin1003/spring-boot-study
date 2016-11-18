package com.minbo.springdemo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.minbo.springdemo.web.listener.MyApplicationEnvironmentPreparedEventListener;
import com.minbo.springdemo.web.listener.MyApplicationFailedEventListener;
import com.minbo.springdemo.web.listener.MyApplicationPreparedEventListener;
import com.minbo.springdemo.web.listener.MyApplicationReadyEventListener;
import com.minbo.springdemo.web.listener.MyApplicationStartedEventListener;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 参考网址： 
 * 1. https://www.tianmaying.com/tutorial/spring-mvc-quickstart 
 * 2. https://www.tianmaying.com/tutorial/spring-boot-overview
 * 
 * @author Minbo.He
 */
@SpringBootApplication
public class Application {
	// 启动入口
	public static void main(String[] args) {
		
		//spring boot实战：在启动过程中增加事件监听机制
//		SpringApplication app = new SpringApplication(Application.class);
//		app.addListeners(new MyApplicationStartedEventListener());
//		app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
//		app.addListeners(new MyApplicationPreparedEventListener());
//		app.addListeners(new MyApplicationReadyEventListener());
//		app.addListeners(new MyApplicationFailedEventListener());
//		app.run(args);

		SpringApplication.run(Application.class, args);
		//new SpringApplicationBuilder(Application.class).web(false).run(args);
	}
}
