package com.md.demo.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * Title: StrUtil.java Description: 字符处理公共类

 *         	<li>1.getFormData() 封装request请求参数为HashMap</li>
 *         	<li>2.getNoNullNewStr() 去NULL 用传入得参数代替</li>
 *         	<li>3.dateToString()	日期转化为字符串</li>
 *         	<li>4.datetimeToString()	日期时间转化为字符串</li>
 *         	<li>5.isEmpty()	判断字符串是否无效</li>
 *         	<li>6.strFormat()	字符串为空或去掉前后空格处理</li>
 *         	<li>7.isNumber() 判断是否数字</li>
 *         	<li>8.getOPERATEID()	生成受理号</li>
 *         	<li>9.encodeFileName()	将文件名进行转码，避免乱码</li>
 *         	<li>10.getFlowNo()	获取流水号(20位)</li>
 *         	<li>11.formatValue()	格式化值格式</li>
 *         </ul>
 */
public class StrUtil {

	/**
	 * 处理json解析不了的特殊字符
	 * @param paramValue
	 * @return
	 */
	public static String transferString(String paramValue) {
		if (!"".equals(paramValue) && paramValue != null) {
			paramValue = paramValue.replaceAll("\"", "“");
			//paramValue = paramValue.replaceAll("\"", "”");
			paramValue = paramValue.replaceAll("\'", "‘");
			paramValue = paramValue.replaceAll("\\\\", " ");
			paramValue = paramValue.replaceAll("&apos;", "‘");
			paramValue = paramValue.replaceAll("\n", " ");
			paramValue = paramValue.replaceAll("\r", " ");			
			//paramValue = paramValue.replaceAll("\'", "’");
		}
		return paramValue;
	}
	/*********
	 * 处理工单流转备注
	 * @param paramValue
	 * @return
	 * @author xiaojianfeng
	 * @updateDate:2015-11-04
	 */
	public static String transFerStringMemo(String paramValue){
		if (!"".equals(paramValue) && paramValue != null) {
			paramValue = paramValue.replaceAll("\"", "“");
			paramValue = paramValue.replaceAll("\'", "‘");
			paramValue = paramValue.replaceAll("\\\\", " ");
			paramValue = paramValue.replaceAll("&apos;", "‘");
			paramValue = paramValue.replaceAll("\r\n", "<br>");
		}
		return paramValue;
	}
	
	/**
	 * 去NULL 用传入得参数代替
	 * @param oldStr
	 * @param rel
	 * @return
	 */
	public static String getNoNullNewStr(String oldStr, String rel) {
		String newStr = (oldStr == null ? rel : oldStr.trim());
		return newStr;
	}
	
	/**
	 * 日期转化为字符串
	 * @param date	日期
	 * @return	如：2015-01-08
	 * @throws Exception
	 */
	public static String dateToString(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 * 日期时间转化为字符串
	 * @param date	日期时间
	 * @return	如：2015-01-08 12:12:12
	 * @throws Exception
	 */
	public static String datetimeToString(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sdf.format(date);
	}
	
	//add by zhangdulong 20150917 增加替换掉特殊字符
	public static final String filterHF(Object out) {
		
		if (out == null || out.toString().length() == 0)
			return "";
		String sout = out.toString();
		StringBuffer sb = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < sout.length(); i++) {
			ch = sout.charAt(i);
			if (ch == '<') {
				sb.append("&lt;");
			} else if (ch == '>') {
				sb.append("&gt;");
			} else if (ch == '&') {
				sb.append("&amp;");
			} else if (ch == '"') {
				sb.append("&quot;");
			} else if (ch == '\'') {
				sb.append("&#39;"); // &acute
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	
	public static final String filterHF(String out) {
		if (out == null || out.length() == 0)
			return out;
		StringBuffer sb = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < out.length(); i++) {
			ch = out.charAt(i);
			if (ch == '<') {
				sb.append("&lt;");
			} else if (ch == '>') {
				sb.append("&gt;");
			} else if (ch == '&') {
				sb.append("&amp;");
			} else if (ch == '"') {
				sb.append("&quot;");
			} else if (ch == '\'') {
				sb.append("&#39;"); // &acute
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	public static final String filterSS(String out) {
		if (out == null || out.length() == 0)
			return out;
		StringBuffer sb = new StringBuffer();
		char ch = ' ';
		for (int i = 0; i < out.length(); i++) {
			ch = out.charAt(i);
			if (ch == '&') {
				sb.append("&amp;");
			} else if (ch == '"') {
				sb.append("&quot;");
			} else if (ch == '\'') {
				sb.append("&#39;"); // &acute
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	/**
	 * 去掉字符串前后空格
	 * @param sValue
	 * @return
	 */
	public static String getString(String sValue) {
		return (sValue == null) ? "" : sValue.trim();
	}

	// 必须是|1|2|3|否则最后一个为&ERROR&
	static public String getFieldData(String AString, int Index, char Dilimiter) {
		int p1, p2;
		int count;
		p1 = 0;
		p2 = 0;
		count = 0;
		if (AString == null)
			AString = "";
		for (int i = 0; i < AString.length(); i++) {
			if (AString.charAt(i) == Dilimiter) {
				p2 = p1;
				p1 = i + 1;
				count++;
			}
			if (count == Index) {
				break;
			}
		}

		if ((p1 > 0) && (count == Index))
			return AString.substring(p2, p1 - 1).trim();
		else
			return "&ERROR&";
	}

	/**
	 * 判断字符串是否无效
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())
				|| "NULL".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 字符串为空或去掉前后空格处理
	 * @param str
	 * @return
	 */
	public static String strFormat(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())
				|| "NULL".equals(str.trim())) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 校验输入串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean rtn = true;
		String array[] = str.split("");// 将字符串转换为字符串数组，以""为分隔符来转换字符串。注：array[0]=""
		int length = array.length;
		for (int i = 1; i < length; i++) { // 从array[1]开始判断
			int code = array[i].hashCode(); // 获得ASCII码
			// 如果array[i]为数字
			if (code >= 48 && code <= 57) { // 判断是否为数字
				continue;
			} else {
				rtn = false;
				break;
			}
		}

		return rtn;
	}

	/**
	 * 生成受理号
	 * 
	 * @param OPERATETYPE
	 *            工单编号
	 * @param flowno
	 *            流水号
	 * @return String
	 * */
	public static String getOPERATEID(String OPERATETYPE, String flowno) {

		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		StringBuffer sb = new StringBuffer(date);
		sb.append(OPERATETYPE).append(flowno);
		return sb.toString();

	}

	/**
	 * 将文件名进行转码，避免乱码
	 * @param request
	 * @param fileName
	 * @author xiajia
	 * @return
	 */
	public static String encodeFileName(HttpServletRequest request,
			String fileName) {
//		String agent = request.getHeader("USER-AGENT");
//		try {
//			if (null != agent && -1 != agent.indexOf("MSIE")) {
//				return URLEncoder.encode(fileName, "UTF-8");
//			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
//				return "=?UTF-8?B?"
//						+ new String(Base64.encodeBase64(fileName
//								.getBytes("UTF-8"))) + "?=";
//			} else {
//				return fileName;
//			}
//		} catch (UnsupportedEncodingException e) {
//		}
		return "";
	}
	
	/**
	 * 1.将null对象返回空字符串-“""”<br>
	 * 2.若非null对象返回toString()及trim字符串<br>
	 * 
	 * @param origin
	 *            String
	 * @return String
	 */
	public static String null2Str(Object origin) {
		return (origin == null ? "" : origin.toString().trim()).replace("null", "");
	}
	
	/**
	 * 格式化值格式
	 * @param value 值
	 * @param valueFormat 值格式(保留2位小数，若显示88.00%，则传入0.00%;若显示88.88，则传入0.00。以此类推！)
	 * @author xuwei
	 * @return
	 */
	public static String formatValue(String value,String valueFormat){
		String digits = "";//默认0位，即取整数
		NumberFormat numberFormat = null;
		if(valueFormat.indexOf("%") > 0){
			//带%值格式
			digits = valueFormat.substring(0, valueFormat.indexOf("%"));//去掉%
			numberFormat = NumberFormat.getPercentInstance();
		}else{
			//不带%值格式
			digits = valueFormat;
			numberFormat = NumberFormat.getNumberInstance();
		}
		if(digits.indexOf(".") > 0){
			//含小数点，取小数点后面的位数
			digits = digits.substring(digits.indexOf(".")+1, digits.length());
		}else{
			digits = "";
		}
		
		int decimals = digits.length();//小数位数
		numberFormat.setMinimumFractionDigits(decimals);
		numberFormat.setMaximumFractionDigits(decimals);
		numberFormat.setGroupingUsed(false);//不千分位显示
		return numberFormat.format(Double.parseDouble(value));
	}
	
	public static String parseSosMgTyp(String mgTyp){
		if(mgTyp.indexOf("Sos")>0){
			return "求助";
		}else if(mgTyp.indexOf("Fire")>0){
			return "火警";
		}else if(mgTyp.indexOf("Aleak")>0){
			return "漏水";
		}else if(mgTyp.indexOf("Hijack")>0){
			return "被劫持";
		}else if(mgTyp.indexOf("War")>0){
			return "预警";
		}else if(mgTyp.indexOf("Alm")>0){
			return "报警";
		}
		return "未知";
	}
	
}