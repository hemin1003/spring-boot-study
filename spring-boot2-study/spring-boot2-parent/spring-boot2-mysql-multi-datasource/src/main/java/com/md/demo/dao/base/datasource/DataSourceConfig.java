package com.md.demo.dao.base.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Primary
	@Qualifier("master")
	@Bean(name = "master")
	@ConfigurationProperties(prefix = "spring.datasource.master") // application.yml中对应属性的前缀
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Qualifier("slave")
	@Bean(name = "slave")
	@ConfigurationProperties(prefix = "spring.datasource.slave") // application.yml中对应属性的前缀
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create().build();
	}
}