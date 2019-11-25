package com.md.demo.util;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * http响应码工具类
 * 
 * @author Minbo
 *
 */
@Slf4j
public class HttpStatusCodeUtil {

	/**
	 * 设置http响应码
	 * 
	 * @param response
	 * @param statusCode
	 */
	public static void setCode(HttpServletResponse response, Integer statusCode) {
		try {
			response.setStatus(statusCode);
		} catch (Exception ex) {
			log.error("设置http响应码异常：" + ex.getMessage(), ex);
		}
	}
}
