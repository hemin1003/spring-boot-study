package com.md.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.md.demo.service.DemoService;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;

/**
 * 测试用的 Controller 类；
 */
@Controller
public class TestController {

	@Autowired
	private DemoService demoService;

	/**
	 * 测试 JSON 接口；
	 * 
	 * @param name 名字
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public JsonResult testJson(String name) {
		String result = this.demoService.sayHello(name);
		return new JsonResult(ResultCode.SUCCESS, result);
	}
}