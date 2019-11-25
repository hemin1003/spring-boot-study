package com.md.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Minbo
 */
@RestController
@Slf4j
public class InitController {

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@ApiOperation(value = "/hello 欢迎入口", httpMethod = "GET")
	@RequestMapping(value = "/hello")
	public String hello() {
		log.info("hello");
		return "Hello greetings from spring-boot2-log-level";
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