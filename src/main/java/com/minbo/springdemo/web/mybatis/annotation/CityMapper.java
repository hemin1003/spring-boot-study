package com.minbo.springdemo.web.mybatis.annotation;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CityMapper {

	@Insert("insert into city(name, state, country) values(#{name}, #{state}, #{country})")
	public int insertCity(@Param("name") String name, @Param("state") String state, @Param("country") String country);

	 @Delete("delete from city where id=#{id}")
	 public int deleteCity(@Param("id") int id);

	@Select("select * from city where state = #{state}")
	public City findByState(@Param("state") String state);

	@Update("update city set name=#{name}, state=#{state},country=#{country} where id=#{id}")
	public int updateCity(@Param("id") int id, @Param("name") String name, @Param("state") String state,
			@Param("country") String country);

}
