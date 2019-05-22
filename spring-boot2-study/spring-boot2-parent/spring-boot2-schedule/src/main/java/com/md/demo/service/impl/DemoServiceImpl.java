package com.md.demo.service.impl;

import org.springframework.stereotype.Service;

import com.md.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello() {
		return "hello from service layer";
	}
	
}
