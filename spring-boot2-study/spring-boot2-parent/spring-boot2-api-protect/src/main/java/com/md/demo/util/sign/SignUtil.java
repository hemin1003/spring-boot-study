package com.md.demo.util.sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 签名工具类
 */
public class SignUtil {

	protected static Logger logger = LoggerFactory.getLogger(SignUtil.class);

	/**
	 * 算法实现 将参数集合按照参数名的ASCII排列 把排序后的结果按照【参数+参数值 +
	 * &】的方式拼接，再加上secretKey=secretKeyValue
	 * 拼装好的字符串按MD5(p1=v1&p2=v2&p3=v3&secretKey=secretKeyValue)进行md5加密后，转大写
	 * 
	 * @param params    参数集合（必须）
	 * @param secretKey 秘钥（必须）
	 * @return
	 */
	public static String signByMD5(Map<String, Object> params, String secretKey) {
		// 将参数集合按照参数名首字母先后顺序排列
		SortedMap<String, Object> sortParamMap = SignUtil.sortMap(params);
		// 把排序后的结果按照参数+参数值的方式拼接
		// 拼装好的字符串按secretKey进行md5加密后，转大写
		return SignUtil.createSign(sortParamMap, secretKey);
	}

	/**
	 * 把排序后的结果按照【参数+参数值 + &】的方式拼接，再加上secretKey=secretKeyValue
	 * 拼装好的字符串按MD5(p1=v1&p2=v2&p3=v3&secretKey=secretKeyValue)进行md5加密后，转大写
	 * 
	 * @param parameters
	 * @param secretKey
	 * @return
	 */
	private static String createSign(Map<String, Object> parameters, String secretKey) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			// 去掉带sign的项
			if (null != value && !"".equals(value) && !"sign".equals(key) && !"secretKey".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		sb.append("secretKey=" + secretKey);
		// 注意sign转为大写
		return MD5Util.encodeByMD5(sb.toString()).toUpperCase();
	}

	/**
	 * 按首字母排列
	 * 
	 * @param map
	 * @return
	 */
	public static SortedMap<String, Object> sortMap(Map<String, Object> map) {
		List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
		// 排序
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
			public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
				// 按首字母比对
				return (String.valueOf(o1.getKey().charAt(0))).compareTo(String.valueOf(o2.getKey().charAt(0)));
			}
		});
		// 排序后
		SortedMap<String, Object> sortmap = new TreeMap<String, Object>();
		// 根据key进行排序ASCII顺序
		System.out.println(infoIds.toString());
		for (int i = 0; i < infoIds.size(); i++) {
			String[] split = infoIds.get(i).toString().split("=");
			if (split.length == 1) {
				sortmap.put(split[0], null);
				continue;
			}
			sortmap.put(split[0], split[1]);
		}
		return sortmap;
	}

	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "minbo");
		params.put("age", 100);
		String secretKey = "996";
		String sign = SignUtil.signByMD5(params, secretKey);
		System.out.println(sign);
	}
}