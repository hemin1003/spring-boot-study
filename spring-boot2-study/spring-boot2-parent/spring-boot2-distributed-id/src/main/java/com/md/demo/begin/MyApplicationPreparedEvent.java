package com.md.demo.begin;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import com.md.core.leafid.MdIdsGen;

/**
 * 监听器：项目初始化时调用类
 * 
 * ApplicationPreparedEvent：spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 * 
 * @author Minbo
 */
public class MyApplicationPreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {

	private Logger logger = LoggerFactory.getLogger(MyApplicationPreparedEvent.class);

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		try {
			ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
			logger.info("项目启动前，执行操作：");
			
			// 1. 初始化分布式id生成器（利用本机IP和端口生成）
			logger.info("1. 初始化分布式id生成器（利用本机IP和端口生成）：");
			String ip = InetAddress.getLocalHost().getHostAddress();
			String port = environment.getProperty("server.port");
			String zkAddress = environment.getProperty("leaf.zk.list");
			MdIdsGen.init(zkAddress, ip, port);

		} catch (Exception e) {
			logger.error("初始化分布式id生成器，异常：" + e.getMessage(), e);
			System.exit(0);
		}
	}
}