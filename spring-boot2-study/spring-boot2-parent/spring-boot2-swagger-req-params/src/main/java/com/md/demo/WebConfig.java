package com.md.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 swagger-ui.html 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 解决 doc.html 404 报错
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");

    }

	/**
	 * 可定义多个拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 定义过滤拦截的url名称，拦截所有请求
		registry.addInterceptor(this.getMyInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
