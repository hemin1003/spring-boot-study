package com.md.demo.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.test.entity.vo.Myuser;
import com.md.demo.test.service.IMyuserService;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MMinbo
 * @since 2019-10-25
 */
@RestController
@RequestMapping("/test/myuser")
public class MyuserController {

	@Autowired
	private IMyuserService myuserService;

	@ApiOperation(value = "获得用户列表", notes = "", httpMethod = "GET")
	@RequestMapping(value = "/listUser")
	public JsonResult listUser() {
		List<Myuser> list = this.myuserService.list(null);
		return new JsonResult(ResultCode.SUCCESS, list);
	}
}
