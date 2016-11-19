package com.minbo.springdemo.web.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 管理接口Demo：登录……等
 */ 
@RestController
@RequestMapping("/api/admin")  
public class AdminController {
	private String userName = "admin";
	private String pw = "123456";

	/**
	 * 登录
	 * @param response：用于保存token到cookie中
	 * @param map：包含userName和password
	 * @return
	 * 
	 * 调用测试：http://localhost:8080/api/admin/login?userName=admin&password=123456
	 */
	@RequestMapping("/login")
	public JsonResult login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> map) {
		if (userName.equals(map.get("userName")) && pw.equals(map.get("password"))) {
			return new JsonResult(ResultCode.SUCCESS, "登录成功！", null);
		} else {
			return new JsonResult(ResultCode.NOT_LOGIN, "登录失败！", null);
		}
	}
}
