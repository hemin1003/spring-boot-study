package com.md.demo.pay.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;

import com.md.demo.pay.utils.variation.WeixinPayConfig;

/**
 * 微信支付随机字符串和签名工具
 */
public class PayCommonUtil {

	/**
	 * 自定义长度随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String createConceStr(int length) {
		String strs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String str = "";
		for (int i = 0; i < length; i++) {
			char achar = strs.charAt(new Random().nextInt(strs.length() - 1));
			str += achar;
		}
		return str;
	}

	/**
	 * 默认16 位随机字符串
	 * 
	 * @return
	 */
	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	/**
	 * 签名工具 FOR PUBLIBC
	 * 
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	public static String createSignPublic(String characterEncoding, Map<String, Object> parameters,
			String projectFlag) {
		String keyWechat = WeixinPayConfig.KEY;
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();// 去掉带sign的项
			if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		sb.append("key=" + keyWechat);
		// 注意sign转为大写
		return MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
	}

	/**
	 * 签名工具 不含商户密钥 －暂时不用
	 * 
	 * @param characterEncoding 编码格式 UTF-8
	 * @param parameters
	 * @return
	 */
	public static String createSignNoKey(String characterEncoding, Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();// 去掉带sign的项
			if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		String signStr = sb.toString();
		String subStr = signStr.substring(0, signStr.length() - 1);
		// 注意sign转为大写
		return MD5Util.MD5Encode(subStr, characterEncoding).toUpperCase();
	}

	/**
	 * @date
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters 请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)) {
				sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
			} else {
				sb.append("<" + key + ">" + value + "</" + key + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * @date
	 * @Description：返回给微信的参数
	 * @param return_code 返回编码
	 * @param return_msg  返回信息
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}

}
