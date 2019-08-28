package com.md.demo.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;
import com.md.demo.util.sign.NetworkUtil;
import com.md.demo.util.sign.SignUtil;

/**
 * @author Minbo
 */
@RestController
public class InitRest {

	protected static Logger logger = LoggerFactory.getLogger(InitRest.class);

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		return "Hello greetings from spring-boot2-api-protect";
	}

	/**
	 * 利用秘钥生成签名（只有对方知，服务器知），校验请求源合法性，不同源可以设置不同的秘钥
	 */
	private static final String API_SECRET_KEY = "996";;

	/**
	 * http://localhost:9090/test?name=minbo&age=100&sign=495FC6F52324AB1460C95A27803E3A4A
	 * 
	 * @param name
	 * @param age
	 * @param sign 大写
	 * @return
	 */
	@GetMapping("/test")
	public JsonResult test(String name, Integer age, String sign, HttpServletRequest request) {
		// 1. 还可以在参数中增加一个动态随机字符参数，比如sId，每次请求时，对方都需要动态生成一个十位随机字符，防止sign值一直固化不变
		// 2. 同时，服务器可以校验请求是否重复，比如可以通过redis存储已请求过的rId（可设置过期时间，以免一直存储历史的rId值），防止别人利用固定请求链接刷请求
		// 3. 可以使用公网ip，限制同一个ip访问次数（也可以在nginx层做限制，这个自行网上了解了）

//		// 获取公网ip
//		String sIp = NetworkUtil.getIpAddress(request);
//		System.out.println("sIp=" + sIp);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("age", age);
		String serverSign = SignUtil.signByMD5(params, API_SECRET_KEY);
		if (serverSign.equals(sign)) {
			return new JsonResult(ResultCode.SUCCESS, "签名通过");
		}
		return new JsonResult(ResultCode.SUCCESS_FAIL, "非法请求");
	}
}