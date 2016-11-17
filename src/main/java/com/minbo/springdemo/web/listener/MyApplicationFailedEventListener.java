package com.minbo.springdemo.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring boot启动异常时执行事件 
 */
public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
	
	protected static Logger logger = LoggerFactory.getLogger(MyApplicationFailedEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationFailedEvent event) {
		Throwable e = event.getException();
		this.handleThrowable(e);
	}
	
	private void handleThrowable(Throwable e){
		logger.info("MyApplicationFailedEventListener. You can do something here.");
	}
}