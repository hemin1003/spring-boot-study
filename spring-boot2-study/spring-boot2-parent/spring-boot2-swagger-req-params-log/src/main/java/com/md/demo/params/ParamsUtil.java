package com.md.demo.params;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

public class ParamsUtil {

	@SuppressWarnings("rawtypes")
	public static String getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement().toString().toUpperCase();
			if (key.contains("USERID") || key.contains("TIMESTAMP") || key.contains("SID") || key.contains("SIGN")) {
				String value = request.getHeader(key);
				map.put(key, value);
			}
		}
		return JSON.toJSONString(map);
	}

	// 去除字符串中的空格、回车、换行符、制表符
	public static String replace(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
