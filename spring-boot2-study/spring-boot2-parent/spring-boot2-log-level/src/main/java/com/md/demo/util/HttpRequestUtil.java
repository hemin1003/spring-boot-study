package com.md.demo.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestUtil {

	protected static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	/**
	 * 用于接口检测 向指定URL发送GET方法的请求
	 * 
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
//			 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送GET请求出现异常：" + e.getMessage() + ", url=" + url);
			result = "";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
				result = "";
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);

//			SocketAddress address = new InetSocketAddress("1.194.74.2", 29082);
//			Proxy proxy = new Proxy(Type.HTTP, address);
			// 打开和URL之间的连接
//			URLConnection connection = realUrl.openConnection(proxy);

//			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
//			 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送GET请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
			result = "";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
				result = "";
			}
		}
		return result;
	}

	public static String sendGet2(String url, String param) {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			String urlStr = url + "?" + param;
			URL realUrl = new URL(urlStr);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept-Encoding", "gzip");
			conn.connect();
			GZIPInputStream gZIPInputStream = null;
			String encoding = conn.getContentEncoding();
			if ("gzip".equals(encoding)) {
				gZIPInputStream = new GZIPInputStream(conn.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, "utf-8"));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					// 转化为UTF-8的编码格式
					line = new String(line.getBytes("UTF-8"));
					stringBuffer.append(line);
				}
				bufferedReader.close();
				return stringBuffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url   发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url   发送请求的 URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param, int timeout) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage());
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url   发送请求的 URL
	 * @param param 请求参数，请求参数应该是 json 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendJsonPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
//			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//			conn.setRequestProperty("accept", "application/json;charset=utf-8");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求,多一个Accept-Encoding类型
	 * 
	 * @param url   发送请求的 URL
	 * @param param 请求参数，请求参数应该是 json 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendJsonPost2(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept-Encoding", "gzip");
//			conn.setRequestProperty("accept", "application/json");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// 定义一个内存输出流
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			GZIPInputStream gis = new GZIPInputStream(conn.getInputStream());
			int len1 = -1;
			byte[] b1 = new byte[1024];
			while ((len1 = gis.read(b1)) != -1) {
				byteArrayOutputStream.write(b1, 0, len1);
			}
			byteArrayOutputStream.close();
			return byteArrayOutputStream.toString();
		} catch (Exception e) {
			// logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" +
			// param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
			}
		}
		return result;
	}

	/**
	 * Gzip模式下的自定义表头发送post请求
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return
	 */
	public static String sendJsonPost3(String url, String param, Map<String, String> header) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
			if (!header.isEmpty()) {
				for (Map.Entry<String, String> m : header.entrySet()) {
					conn.setRequestProperty(m.getKey(), m.getValue());
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// 定义一个内存输出流
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			GZIPInputStream gis = new GZIPInputStream(conn.getInputStream());
			int len1 = -1;
			byte[] b1 = new byte[1024];
			while ((len1 = gis.read(b1)) != -1) {
				byteArrayOutputStream.write(b1, 0, len1);
			}
			byteArrayOutputStream.close();
			return byteArrayOutputStream.toString();
		} catch (Exception e) {
			// logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" +
			// param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
			}
		}
		return result;
	}

	public static String sendJsonPost(String url, String param, Map<String, String> header) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
//			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//			conn.setRequestProperty("accept", "application/json;charset=utf-8");
			if (!header.isEmpty()) {
				for (Map.Entry<String, String> m : header.entrySet()) {
					conn.setRequestProperty(m.getKey(), m.getValue());
				}
			}
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
			}
		}
		return result;
	}

	/**
	 * 正常POST请求下的全自定义HEAD头方法
	 * 
	 * @param url
	 * @param param
	 * @param header
	 * @return
	 */
	public static String sendJsonPostByCustomHead(String url, String param, Map<String, String> header) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 设置通用的请求属性
//			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//			conn.setRequestProperty("accept", "application/json;charset=utf-8");
			if (!header.isEmpty()) {
				for (Map.Entry<String, String> m : header.entrySet()) {
					conn.setRequestProperty(m.getKey(), m.getValue());
				}
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送POST请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url   发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param, int timeout) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
//			 获取所有响应头字段
//			Map<String, List<String>> map = connection.getHeaderFields();
//			// 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送GET请求出现异常：" + e.getMessage() + ", url=" + url + ", param=" + param);
			result = "";
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				logger.error("关闭流异常：" + e2.getMessage(), e2);
				result = "";
			}
		}
		return result;
	}
}
