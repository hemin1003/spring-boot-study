package com.md.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.md.demo.dao.CityDao;
import com.md.demo.service.CityService;
import com.md.demo.vo.CityVo;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	/**
	 * 获得城市1列表
	 */
	@Override
	public List<CityVo> listCities111() {
		return this.cityDao.listCities111();
	}

	/**
	 * 获得城市2列表
	 */
	@Override
	public List<CityVo> listCities222() {
		return this.cityDao.listCities222();
	}

}
