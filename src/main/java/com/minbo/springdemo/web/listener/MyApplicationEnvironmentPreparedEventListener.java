package com.minbo.springdemo.web.listener;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * spring boot 配置环境事件监听
 * 
 * 在该监听中获取到ConfigurableEnvironment后可以对配置信息做操作，例如：修改默认的配置信息，增加额外的配置信息等等
 */
public class MyApplicationEnvironmentPreparedEventListener
		implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

	protected static Logger logger = LoggerFactory.getLogger(MyApplicationEnvironmentPreparedEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		ConfigurableEnvironment envi = event.getEnvironment();
		MutablePropertySources mps = envi.getPropertySources();
		if (mps != null) {
			Iterator<PropertySource<?>> iter = mps.iterator();
			while (iter.hasNext()) {
				PropertySource<?> ps = iter.next();
				logger.info("ps.getName:{};ps.getSource:{};ps.getClass:{}", ps.getName(), ps.getSource(),
						ps.getClass());
			}
		}
	}
}
