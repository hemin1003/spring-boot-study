package com.minbo.springdemo.web.mybatistest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	int save(User user);

	User selectById(Integer id);

	int updateById(User user);

	int deleteById(Integer id);

	List<User> queryAll();
}
