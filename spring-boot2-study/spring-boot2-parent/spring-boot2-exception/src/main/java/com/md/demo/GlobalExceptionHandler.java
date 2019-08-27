package com.md.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理
 * 
 * @author Minbo
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	protected static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		logger.error("系统异常【全局异常】：" + e.getMessage(), e);
		return "error";
	}
}
