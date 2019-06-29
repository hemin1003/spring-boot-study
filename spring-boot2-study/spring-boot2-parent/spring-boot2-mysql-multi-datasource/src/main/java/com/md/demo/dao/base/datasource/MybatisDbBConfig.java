package com.md.demo.dao.base.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = { "mybatis.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisDbBConfig {
	
	private String localMapper = "classpath:mybatis/mapper/*.xml";
	
	@Autowired
	@Qualifier("slave")
	private DataSource ds2;

	@Bean
	public SqlSessionFactory sqlSessionFactory2() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds2);
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(localMapper));
		return factoryBean.getObject();
	}

	@Bean(name = "slaveSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2());
		return template;
	}
}
