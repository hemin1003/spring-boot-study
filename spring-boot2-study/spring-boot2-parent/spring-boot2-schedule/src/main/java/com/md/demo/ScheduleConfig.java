package com.md.demo;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 并行多线程任务功能（方式一）
 * 
 * @author Minbo.He
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// 开启一个固定10个大小的线程池，也使用Executors下其他的线程池
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
	}
}