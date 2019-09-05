package com.md.demo.oauth.opaque.ds.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String ROOT_PATTERN = "/**";

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 排除路径验证
				.antMatchers("/hello")
					.permitAll()
				.antMatchers(ROOT_PATTERN)
					.authenticated();
	}
}
