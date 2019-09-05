package com.md.demo.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器定义
 * 
 * @author Minbo.He
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	// 让bean提前加载，让拦截器中的@Autowired生效
	@Bean
	public HandlerInterceptor getMyInterceptor() {
		return new MyHttpInterceptor();
	}

	/**
	 * 可定义多个拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 定义过滤拦截的url名称，拦截所有请求
		registry.addInterceptor(this.getMyInterceptor()).addPathPatterns("/**");
//		registry.addInterceptor(其他拦截器).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
