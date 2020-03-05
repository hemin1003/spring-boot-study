package com.md.demo.pay.utils.variation;

/**
 * 微信公众号支付配置信息
 */
public class WeixinPayConfig {

//	// APPID（可固化，也可以动态变，DB中可同时记录AppID）
//	public final static String APPID = "xxxxxx";

	// 公众平台商户ID
	public final static String MCHID = "xxxxxx";

	// 公众平台商户KEY
	public final static String KEY = "xxxxxx";

	// 微信企业支付证书
	public static String CERT_FILE = System.getProperty("user.dir") + System.getProperty("file.separator") + "cert"
			+ System.getProperty("file.separator") + "apiclient_cert.p12";
}