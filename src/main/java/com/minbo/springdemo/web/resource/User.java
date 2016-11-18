package com.minbo.springdemo.web.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User {
	//绑定到资源文件*.properties中的值
	private @Value("${name}") String name;
	
	private @Value("${age}") int age;
	
	private @Value("${address}") String address;
	
	private @Value("${remark}") String remark;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
