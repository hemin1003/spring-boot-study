package com.md.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.util.HttpRequestUtil;
import com.md.demo.util.JsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Minbo
 */
@RestController
@RequestMapping("/log")
@Api(tags = { "接口-演示" })
@Slf4j
public class LogController {

	@Autowired
	private Environment environment;

	/**
	 * http://localhost:9090/testLog
	 * 
	 * @return
	 */
	@GetMapping("/testLog")
	public String testLog() {
		log.debug("this is debug log");
		log.info("this is info log");
		return "Hello greetings from spring-boot2-log-level";
	}

	/**
	 * http://localhost:9090/getLogger
	 * 
	 * @return
	 */
	@ApiOperation(value = "获得当前项目：日志输出级别", httpMethod = "GET")
	@GetMapping("/getLogger")
	public JsonResult getLogger() {
		String port = this.environment.getProperty("local.server.port");
		String url = "http://localhost:" + port + "/actuator/loggers/com.md";
		String result = HttpRequestUtil.sendGet(url);
		log.info("当前项目：日志输出级别，url={}，result={}", url, result);
		return JsonResult.ok(result);
	}

	/**
	 * http://localhost:9090/setLogger
	 * 
	 * @return
	 */
	@ApiOperation(value = "设置当前项目：日志输出级别（INFO/DEBUG）", httpMethod = "POST")
	@PostMapping("/setLogger")
	public JsonResult setLogger(@RequestHeader String logLevel) {
		String port = this.environment.getProperty("local.server.port");
		String url = "http://localhost:" + port + "/actuator/loggers/com.md";
		String param = "{\"configuredLevel\":\"" + logLevel + "\"}";
		String result = HttpRequestUtil.sendJsonPost(url, param);
		log.info("设置当前项目：日志输出级别，url={}，param={}，result={}", url, param, result);
		return JsonResult.ok();
	}
}