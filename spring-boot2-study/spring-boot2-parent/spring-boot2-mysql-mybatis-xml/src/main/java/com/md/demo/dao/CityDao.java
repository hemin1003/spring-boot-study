package com.md.demo.dao;

import java.util.List;

import com.md.demo.vo.CityVo;

public interface CityDao {

	public List<CityVo> listCities();

	public CityVo getCityById(Long id);

}
