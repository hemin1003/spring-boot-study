package com.minbo.springdemo.web.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

	@Autowired
	private PersonDtoMapper personDtoMapper;

	public String show() {
		return "hello world!";
	}

	public List<Person> findById(String id) {
		return personDtoMapper.findById(id);
	}
}
