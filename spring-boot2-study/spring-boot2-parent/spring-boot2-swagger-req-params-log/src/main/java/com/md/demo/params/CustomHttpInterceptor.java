package com.md.demo.params;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.md.demo.util.NetworkUtil;

/**
 * 拦截处理类
 * 
 * @author Minbo
 */
@Component
public class CustomHttpInterceptor extends HandlerInterceptorAdapter {

	protected static Logger logger = LoggerFactory.getLogger(CustomHttpInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long start = System.currentTimeMillis();
		String url = request.getRequestURL().toString();
		// 接口过滤打印
		if (url != null && url.contains("/hello")) {
			return true;
		}

		String method = request.getMethod();
		String queryString = "";
		// 去掉最后一个空格
		Map<String, String[]> params = request.getParameterMap();
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		// URL 参数
		queryString = queryString.equals("") ? null : queryString.substring(0, queryString.length() - 1);

		// body 参数
		RequestWrapper requestWrapper = new RequestWrapper(request);
		String bodyParams = ParamsUtil.replace(requestWrapper.getBody());

		// header参数
		String headersParams = ParamsUtil.getHeadersInfo(requestWrapper);

		// 对方IP
		String cIp = NetworkUtil.getIpAddress(request);

		long end = System.currentTimeMillis();

		logger.info(String.format(
				"请求参数, url: %s, method: %s, query-params: %s, body-params: %s, headers-params: %s, c-ip: %s, run-time/ms: %s",
				url, method, queryString, bodyParams, headersParams, cIp, (end - start) + ""));
		return true;
	}
}