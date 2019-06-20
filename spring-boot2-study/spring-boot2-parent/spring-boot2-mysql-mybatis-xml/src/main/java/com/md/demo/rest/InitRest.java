package com.md.demo.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.service.CityService;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;
import com.md.demo.vo.CityVo;

/**
 * @author Minbo
 */
@RestController
public class InitRest {

	protected static Logger logger = LoggerFactory.getLogger(InitRest.class);

	@Autowired
	private CityService cityService;

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		return "Hello greetings from spring-boot2-mysql-mybatis-xml";
	}

	/**
	 * http://localhost:9090/listCities
	 * 
	 * @return
	 */
	@GetMapping("/listCities")
	public JsonResult listCities() {
		List<CityVo> list = this.cityService.listCities();
		return new JsonResult(ResultCode.SUCCESS, list);
	}

	/**
	 * http://localhost:9090/getCityById?id=1
	 * 
	 * @return
	 */
	@GetMapping("/getCityById")
	public JsonResult listCities(Long id) {
		CityVo obj = this.cityService.getCityById(id);
		return new JsonResult(ResultCode.SUCCESS, obj);
	}
}