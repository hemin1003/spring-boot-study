package com.minbo.springdemo.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring boot 启动监听类 
 * 在该事件中可以获取到SpringApplication对象，可做一些执行前的设置
 * 参考资料：http://blog.csdn.net/liaokailin/article/details/48186331
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

	protected static Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		SpringApplication app = event.getSpringApplication();
		// 不显示banner信息
		app.setBannerMode(Mode.OFF);
		logger.info("MyApplicationStartedEventListener...");
	}
}
