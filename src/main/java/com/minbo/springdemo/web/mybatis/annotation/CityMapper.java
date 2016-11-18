package com.minbo.springdemo.web.mybatis.annotation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

	@Select("select * from city where state = #{state}")
	public City findByState(@Param("state") String state);

}
