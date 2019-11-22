package com.md.demo.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.dto.GetByIdDTO;
import com.md.demo.service.IUserService;
import com.md.demo.util.BaseController;
import com.md.demo.util.HttpStatusCodeUtil;
import com.md.demo.util.JsonResult;
import com.md.demo.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Minbo
 */
@RestController
@RequestMapping("/demo")
@Api(tags = { "接口-演示" })
public class DemoController extends BaseController {

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "根据id获得用户信息", notes = "id不能为空", httpMethod = "POST")
	@PostMapping(value = "/getUserById")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JsonResult<UserVO> getUserById(@Validated @RequestBody GetByIdDTO dto, BindingResult result,
			HttpServletResponse response) {
		// 验证参数合法性
		JsonResult validResult = super.getJsonResult(dto, result);
		if (validResult != null) {
			// 设置http响应码，利于监控工具，不要统一使用200
			HttpStatusCodeUtil.setHttpCode(response, 4403);
			return validResult;
		}

		// 逻辑处理
		UserVO obj = this.userService.getUserById(dto);
		return JsonResult.ok(obj);
	}
}