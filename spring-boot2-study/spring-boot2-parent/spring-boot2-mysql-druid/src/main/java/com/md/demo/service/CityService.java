package com.md.demo.service;

import java.util.List;
import com.md.demo.vo.CityVo;

public interface CityService {

	public List<CityVo> listCities();

	public CityVo getCityById(Long id);
}
