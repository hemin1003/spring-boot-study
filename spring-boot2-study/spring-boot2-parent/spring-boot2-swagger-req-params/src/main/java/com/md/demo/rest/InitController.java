package com.md.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.util.JsonResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author Minbo
 */
@RestController
public class InitController {

	protected static Logger logger = LoggerFactory.getLogger(InitController.class);

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@ApiOperation(value = "/hello 欢迎入口", httpMethod = "GET")
	@RequestMapping(value = "/hello")
	public String hello() {
		logger.info("hello");
		return "Hello greetings from spring-boot2-swagger-req-params";
	}

	@ApiOperation(value = "/getUserName 根据用户id获得用户的姓名", notes = "id不能为空", httpMethod = "GET")
	@ApiImplicitParam(dataType = "string", name = "userId", value = "用户id", required = true)
	@RequestMapping(value = "/getUserName")
	@SuppressWarnings({ "rawtypes" })
	public JsonResult getUserName(@RequestHeader String userId) {
		String result = "hello " + userId + "，name=张三";
		return JsonResult.ok(result);
	}

	/**
	 * Swagger注解用法：
	 * 
	 * @Api：修饰整个类，描述Controller的作用
	 * @ApiOperation：描述一个类的一个方法，或者说一个接口
	 * @ApiParam：单个参数描述
	 * @ApiModel：用对象来接收参数
	 * @ApiProperty：用对象接收参数时，描述对象的一个字段
	 * @ApiResponse：HTTP响应其中1个描述
	 * @ApiResponses：HTTP响应整体描述
	 * @ApiIgnore：使用该注解忽略这个API
	 * @ApiError ：发生错误返回的信息
	 * @ApiImplicitParam：一个请求参数
	 * @ApiImplicitParams：多个请求参数
	 */
}