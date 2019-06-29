package com.md.demo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.md.demo.dao.CityDao;
import com.md.demo.dao.base.BaseDao;
import com.md.demo.vo.CityVo;

@Component
public class CityDaoImpl extends BaseDao implements CityDao {

	@Override
	public List<CityVo> listCities111() {
		return this.sqlSessionTemplateOfMaster.selectList("listCities111");
	}

	@Override
	public List<CityVo> listCities222() {
		return this.sqlSessionTemplateOfSlave.selectList("listCities222");
	}

}
