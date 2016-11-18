package com.minbo.springdemo.web.mybatis.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot与Mybatis
 * 使用xml方式集成
 * @author Minbo.He
 */
@RestController
@RequestMapping("/mybatis/xml")
public class ControllerTestForMybatisXml {
	
	protected static Logger logger = LoggerFactory.getLogger(ControllerTestForMybatisXml.class);
	
	@Autowired
	private CityDao cityDao;

	@Autowired
	private HotelMapper hotelMapper;
	
	@RequestMapping("/")
	public String greeting() {
		return "Greetings from Spring Mybatis Xml!";
	}
	
	@RequestMapping("/city")
	public String City1(){
		String city = this.cityDao.selectCityById(1).toString();
		logger.info("city=" + city);
		return city;
	}
	
	@RequestMapping("/hotel")
	public String getCity2(){
		String hotel = this.hotelMapper.selectByCityId(1).toString();
		logger.info("hotel=" + hotel);
		return hotel;
	}
}
