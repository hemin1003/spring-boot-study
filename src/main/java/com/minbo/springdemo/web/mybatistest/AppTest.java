package com.minbo.springdemo.web.mybatistest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = {App.class})  
//相当于  --spring.profiles.active=dev  
@ActiveProfiles(value="dev")  
public class AppTest {
	
	@Autowired
	private UserMapper mapper;

	@Test
	public void testInsert() {
		User user = new User();
		user.setUserName("张三");
		user.setAge(23);
		mapper.save(user);
		System.out.println("插入用户信息" + user.getUserName());
	}
	
}
