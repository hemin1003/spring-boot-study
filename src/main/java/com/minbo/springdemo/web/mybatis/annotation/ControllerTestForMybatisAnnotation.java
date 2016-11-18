package com.minbo.springdemo.web.mybatis.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot与Mybatis
 * 使用Annotation方式集成
 * @author Minbo.He
 */
@RestController
@RequestMapping("/mybatis/annotation")
public class ControllerTestForMybatisAnnotation {
	
	protected static Logger logger = LoggerFactory.getLogger(ControllerTestForMybatisAnnotation.class);
	
	@Autowired
	private CityMapper cityMapper;
	
	@RequestMapping("/")
	public String greeting() {
		return "Greetings from Spring Mybatis Annotation!";
	}
	
	//传值方式1，例如：http://localhost:8081/mybatis/annotation/city1/CA
	@RequestMapping("/city1/{state}")
	public String getCity1(@PathVariable("state") String state){
		String city = this.cityMapper.findByState(state).toString();
		logger.info("city=" + city);
		return city;
	}
	
	//传值方式2，例如：http://localhost:8081/mybatis/annotation/city2?state=CA
	@RequestMapping("/city2")
	public String getCity2(String state){
		String city = this.cityMapper.findByState(state).toString();
		logger.info("city=" + city);
		return city;
	}
	
	//调用例如：http://localhost:8081/mybatis/annotation/addCity?name=hangzhou&state=HZ&country=CH
	@RequestMapping("/addCity")
	public String addCity(String name, String state, String country){
		int result = this.cityMapper.insertCity(name, state, country);
		return this.result(result);
	}
	
	//调用例如：http://localhost:8081/mybatis/annotation/removeCity?id=4
	@RequestMapping("/removeCity")
	public String removeCity(int id){
		int result = this.cityMapper.deleteCity(id);
		return this.result(result);
	}
	
	//调用例如：http://localhost:8081/mybatis/annotation/modifyCity?id=2&name=hangzhou&state=HZ&country=CH
	@RequestMapping("/modifyCity")
	public String modifyCity(int id, String name, String state, String country){
		int result = this.cityMapper.updateCity(id, name, state, country);
		return this.result(result);
	}
	
	private String result(int result){
		logger.info("result=" + result);
		if(result==1){
			return "success";
		}else if(result==0){
			return "failure";
		}
		return "unknown result";
	}
}
