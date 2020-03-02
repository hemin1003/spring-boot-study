package com.md.demo.pay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

import com.md.demo.pay.utils.variation.WeixinPayConfig;

/**
 * 通过微信支付的企业证书建立安全连接
 */
@SuppressWarnings("deprecation")
public class FsClientWithCertSSL {

	// 携带证书的client
	private static CloseableHttpClient httpClient; // 可以优化加上httpclient池来管理httpclient
	// 编码格式 utf-8
	private static String CHARSET = "UTF-8";
	// 指定读取证书格式为PKCS12
	private final static String CEART_MODE = "PKCS12";

//	static {
//		// 设置http请求连接超时时间
//		RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(60000).build();// timeunit
//																												// seconds
//		httpClient = createClient(config);// 创建 client
//	}

	/**
	 * get请求
	 * 
	 * @param url    请求url
	 * @param params 请求参数
	 * @return
	 */
	public static String doGet(String url, Map<String, String> params, String projectFlag) {
		return doGet(url, params, CHARSET, projectFlag);
	}

	/**
	 * post请求
	 * 
	 * @param url    请求url
	 * @param params 请求参数
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> params, String projectFlag) {
		return doPost(url, params, CHARSET, projectFlag);
	}

	/**
	 * post请求
	 * 
	 * @param url       请求地址
	 * @param paramsXml 请求的参数xml格式
	 * @return
	 */
	public static String doPost(String url, String paramsXml, String projectFlag) {
		return doPost(url, paramsXml, CHARSET, projectFlag);
	}

	/**
	 * post请求
	 * 
	 * @param url       请求地址
	 * @param paramsXml 请求的参数xml格式
	 * @param charset   设置编码格式
	 * @return
	 */
	public static String doPost(String url, String paramsXml, String charset, String projectFlag) {
		Args.notNull(url, "请求目标url");
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Connection", "keep-alive");
			httpPost.addHeader("Accept", "*/*");
			httpPost.addHeader("Content-Type", "text/xml; charset=UTF-8");
			httpPost.addHeader("Host", "api.mch.weixin.qq.com");
			httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpPost.addHeader("Cache-Control", "max-age=0");
			httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			if (paramsXml != null) {
				httpPost.setEntity((new StringEntity(paramsXml, ContentType.TEXT_XML.getMimeType(), CHARSET)));
			}
			RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(6000).build();// timeunit
																													// :
																													// seconds
			httpClient = createClient(config, projectFlag);// 创建 client
			CloseableHttpResponse response = httpClient.execute(httpPost);// 异常？
			int statusCode = response.getStatusLine().getStatusCode();
			try {
				if (statusCode != 200) {
					httpPost.abort();
					throw new RuntimeException("HttpClient,error status code :" + statusCode);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, Charsets.UTF_8);
				}
				EntityUtils.consume(entity);
				return result;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}

	/**
	 * HTTP Get 获取内容
	 * 
	 * @param url     请求的url地址 ?之前的地址
	 * @param params  请求的参数
	 * @param charset 编码格式
	 * @return 响应结果
	 */
	public static String doGet(String url, Map<String, String> params, String charset, String projectFlag) {
		Args.notNull(url, "请求目标url");
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			HttpGet httpGet = new HttpGet(url);
			RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(60000).build();// timeunit
																													// :
																													// seconds
			httpClient = createClient(config, projectFlag);// 创建 client
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			try {
				if (statusCode != 200) {
					httpGet.abort();
					throw new RuntimeException("HttpClient,error status code :" + statusCode);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, Charsets.UTF_8);
				}
				EntityUtils.consume(entity);
				return result;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}

	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url     请求的url地址 ?之前的地址
	 * @param params  请求的参数
	 * @param charset 编码格式
	 * @return 响应结果
	 */
	public static String doPost(String url, Map<String, Object> params, String charset, String projectFlag) {
		Args.notNull(url, "请求目标url");
		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String value = (String) entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(60000).build();// timeunit
																													// :
																													// seconds
			httpClient = createClient(config, projectFlag);// 创建 client
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					throw new RuntimeException("HttpClient,error status code :" + statusCode);
				}
				HttpEntity entity = response.getEntity();
				String result = null;
				if (entity != null) {
					result = EntityUtils.toString(entity, Charsets.UTF_8);
				}
				EntityUtils.consume(entity);
				return result;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭连接,释放资源
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}

	/**
	 *
	 * 获取带证书的安全client
	 * 
	 * @param config 请求参数
	 * @return
	 */
	private static CloseableHttpClient createClient(RequestConfig config, String projectFlag) {
		String certFile = WeixinPayConfig.CERT_FILE;
		String mchId = WeixinPayConfig.MCHID;
		CloseableHttpClient httpclient = null;
		try {
			// 指定读取证书格式为PKCS12
			KeyStore keyStore = KeyStore.getInstance(CEART_MODE);
			// 读取本机存放的PKCS12证书文件
			FileInputStream instream = new FileInputStream(new File(certFile));
//			InputStream instream = FsClientWithCertSSL.class.getResourceAsStream(CERT);
			try {
				// 指定PKCS12的密码(商户ID)
				keyStore.load(instream, mchId.toCharArray());
			} catch (CertificateException e) {
				e.printStackTrace();
			} finally {
				instream.close();
			}
			// 相信自己的CA和所有自签名的证书
			// SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(keyStore, new
			// TrustSelfSignedStrategy()).build();
			// Trust own CA and all self-signed certs 加上密钥
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
			// Allow TLSv1 protocol only 指定TLS版本 (IETF Internet Enginnering TaskForce )
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, // TLSv1
																													// 等于
																													// SSLv3
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			// 设置httpclient的SSLSocketFactory
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(config)
					// .setConnectionManager(connManager)
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return httpclient;
	}

}
