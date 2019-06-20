package com.md.demo.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.md.demo.dao.CityDao;
import com.md.demo.vo.CityVo;

@Component
public class CityDaoImpl implements CityDao {

	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<CityVo> listCities() {
		return this.sqlSessionTemplate.selectList("listCities");
	}

	@Override
	public CityVo getCityById(Long id) {
		return this.sqlSessionTemplate.selectOne("getCityById", id);
	}

}
