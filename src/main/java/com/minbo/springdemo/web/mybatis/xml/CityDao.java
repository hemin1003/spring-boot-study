package com.minbo.springdemo.web.mybatis.xml;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDao {

	@Autowired
	private SqlSession sqlSession;

	public City selectCityById(long id) {
		return this.sqlSession.selectOne("selectCityById", id);
	}

}
