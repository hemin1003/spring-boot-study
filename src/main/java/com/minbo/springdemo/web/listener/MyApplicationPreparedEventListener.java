package com.minbo.springdemo.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 上下文创建完成后执行的事件监听器
 * spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 */
public class MyApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

	protected static Logger logger = LoggerFactory.getLogger(MyApplicationPreparedEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		ConfigurableApplicationContext cac = event.getApplicationContext();
		logger.info("MyApplicationPreparedEventListener. You can do something here.");
	}
}
