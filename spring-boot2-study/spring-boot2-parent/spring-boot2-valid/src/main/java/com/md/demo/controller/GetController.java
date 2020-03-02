package com.md.demo.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.controller.base.BaseDTO;
import com.md.demo.dto.GetUserByIdDTO;
import com.md.demo.exception.ParamaErrorException;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Minbo
 */
@RestController
@RequestMapping("/api/")
@Api(tags = { "查询接口" })
@Slf4j
public class GetController {

	/**
	 * 测试Post请求
	 */
	@ApiOperation(value = "TestPost接口", httpMethod = "POST")
	@PostMapping("/test/post")
	public JsonResult testPost(@Valid @RequestBody BaseDTO<GetUserByIdDTO> dto) {
		log.debug("enter test post api...");
		return new JsonResult(ResultCode.SUCCESS);
	}

	/**
	 * 测试Get请求
	 */
	@Validated
	@ApiOperation(value = "TestGet接口", httpMethod = "GET")
	@GetMapping("/test/get/{userName}")
	public JsonResult testGet(@PathVariable String userName) {
		log.debug("enter test get api...");
		if (userName == null || "".equals(userName)) {
			throw new ParamaErrorException("userName 不能为空");
		}
		return new JsonResult(ResultCode.SUCCESS);
	}

}