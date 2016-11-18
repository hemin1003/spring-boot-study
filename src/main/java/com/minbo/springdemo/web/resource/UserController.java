package com.minbo.springdemo.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  spring boot实战：分散配置~读取配置文件中的值
 *  参考资料：http://blog.csdn.net/liaokailin/article/details/48423847
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private User user;

	@RequestMapping("/")
	public String getUser() {
		return "hello " + user.getName() + ", " 
				+ user.getAge() + ", " 
				+ user.getAddress() 
				+ ", remark=" + user.getRemark();
	}
}
