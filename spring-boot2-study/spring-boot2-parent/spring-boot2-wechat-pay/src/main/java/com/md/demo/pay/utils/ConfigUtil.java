package com.md.demo.pay.utils;

/**
 * 微信支付相关配置信息
 */
public class ConfigUtil {
	
	/**
	 * 企业付款地址
	 */
	// 企业付款接口(POST)
	public final static String PROMOTION_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	// 企业付款查询接口(POST)
	public final static String PROMOTION_QUERY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
}
