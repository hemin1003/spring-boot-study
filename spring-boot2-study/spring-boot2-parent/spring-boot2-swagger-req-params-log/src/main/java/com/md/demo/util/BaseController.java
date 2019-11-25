package com.md.demo.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 基类
 * 
 * @author Minbo
 *
 */
public class BaseController {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JsonResult getJsonResult(Object model, BindingResult result) {
		if (result.hasErrors()) {
			StringBuilder stringBuilder = new StringBuilder();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError objectError : allErrors) {
				String defaultMessage = objectError.getDefaultMessage();
				// 验证失败时提示内容一起拼接返回
				stringBuilder.append(defaultMessage).append(";");
			}
			return new JsonResult(CodeEnums.PARA_ERR.getCode(), stringBuilder.toString(), model);
		}
		return null;
	}
}
