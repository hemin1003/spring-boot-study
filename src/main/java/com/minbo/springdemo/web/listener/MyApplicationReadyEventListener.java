package com.minbo.springdemo.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

	protected static Logger logger = LoggerFactory.getLogger(MyApplicationReadyEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ConfigurableApplicationContext cac = event.getApplicationContext();
		logger.info("MyApplicationReadyEvent. You can do something here.");
	}

}
