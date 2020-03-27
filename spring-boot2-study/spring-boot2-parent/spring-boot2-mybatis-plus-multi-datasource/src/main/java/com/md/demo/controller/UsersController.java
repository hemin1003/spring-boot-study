package com.md.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.entity.vo.Users;
import com.md.demo.service.IUsersService;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Minbo
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/demo/users")
public class UsersController {

	@Autowired
	private IUsersService usersService;

	@ApiOperation(value = "获得用户列表信息", httpMethod = "POST")
	@PostMapping("/listDbInfo")
	public JsonResult listDbInfo() {
		List<Users> dataList = this.usersService.listDbInfo();
		if (dataList == null || dataList.size() == 0) {
			return new JsonResult(ResultCode.SUCCESS_FAIL);
		}
		return new JsonResult(ResultCode.SUCCESS, dataList);
	}
}
