package com.minbo.springdemo.ids;

import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 分布式唯一主键ID工具类
 * @author Minbo
 *
 */
public class IdUtils {
	
	protected static Logger logger = LoggerFactory.getLogger(IdUtils.class);
	
	private static IdWorker idWorker;

	static {
		//方案1，从配置文件中获取
		Resource resource = new ClassPathResource(
				"application.properties");
		Properties p = new Properties();
		try {
			p.load(resource.getInputStream());
			Long workerId = Long.valueOf(p.getProperty("worker.id"));
			idWorker = new IdWorker(workerId);
			logger.info("分布式唯一主键ID工具类，[从配置中参数中获取]，workerId=" + workerId);
			
		} catch (Exception e) {
			logger.error("分布式进程id配置文件异常，请检查", new RuntimeException("配置文件异常"));
			idWorker = new IdWorker(Long.valueOf(new Random().nextInt(1000) + 1));
		}
	}

	public static Long getId(){
		return idWorker.nextId();
	}
	
	public static void main(String[] args) {
		System.out.println(IdUtils.getId());
	}
}