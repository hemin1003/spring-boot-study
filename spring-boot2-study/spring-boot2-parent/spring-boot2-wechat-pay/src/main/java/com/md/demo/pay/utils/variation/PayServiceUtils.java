package com.md.demo.pay.utils.variation;

import java.net.InetAddress;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.md.demo.pay.utils.ConfigUtil;
import com.md.demo.pay.utils.FsClientWithCertSSL;
import com.md.demo.pay.utils.MapUtils;
import com.md.demo.pay.utils.PayCommonUtil;
import com.md.demo.pay.utils.StrUtil;
import com.md.demo.pay.utils.XMLUtil;
import com.md.demo.pay.utils.vo.PayWithdrawHis;

import lombok.extern.slf4j.Slf4j;

/**
 * 支付服务工具类
 * 
 * @author Minbo
 *
 */
@Slf4j
public class PayServiceUtils {

	/**
	 * 打款
	 */
	public static int doEpay(String appId, String openId, String ip, String amount, String orderId, String re_user_name,
			PayWithdrawHis objInfo, String projectFlag, String appIdName) {
		if (Strings.isNullOrEmpty(orderId) || Strings.isNullOrEmpty(openId)) {
			log.info("企业支付开始，openid: " + openId + "  orderId: " + orderId);
			return 1;
		}

		int amountInt = Integer.valueOf(amount).intValue();
		if (amountInt < 100 || Strings.isNullOrEmpty(amount)) {
			log.warn("企业支付开始=> 金额不得少于100分(1元)");
			return 2;
		}

		try {
			if (Strings.isNullOrEmpty(ip)) {
				InetAddress addr = InetAddress.getLocalHost();
				ip = addr.getHostAddress().toString();
			}
			log.info("企业支付开始，用户:" + openId + "  订单号:" + orderId + " 金额:" + amount + ", ip=" + ip);

			// 设置支付参数
			SortedMap<String, Object> parameters = getSignParams(appId, orderId, openId, amountInt, ip, re_user_name,
					projectFlag, appIdName);
			parameters.put("sign", PayCommonUtil.createSignPublic(Charsets.UTF_8.toString(), parameters, projectFlag));// sign签名
			String requestXML = PayCommonUtil.getRequestXml(parameters);// 生成xml格式字符串
			String responseStr = FsClientWithCertSSL.doPost(ConfigUtil.PROMOTION_URL, requestXML, projectFlag);

			// 解析结果
			Map<String, Object> resutlMap = XMLUtil.doXMLParse(responseStr);
			log.info("企业付款，微信返回结果：resutlMap=" + resutlMap.toString());

			// 校验响应结果return_code
			if (resutlMap != null && WeixinConstant.FAIL.equalsIgnoreCase(resutlMap.get("return_code").toString())) {
				log.error("企业支付调用失败");
				return 3;
			}
			if (StrUtil.null2Str(resutlMap.get("err_code")).equals("SENDNUM_LIMIT")) {
				return 66;
			}
			if (StrUtil.null2Str(resutlMap.get("err_code")).equals("NOTENOUGH")) {
				return 99;
			}
			if (StrUtil.null2Str(resutlMap.get("err_code")).equals("SYSTEMERROR")
					&& StrUtil.null2Str(resutlMap.get("err_code_des")).equals("系统繁忙，请稍后再试")) {
				return 77;
			}
			if (StrUtil.null2Str(resutlMap.get("err_code")).equals("SYSTEMERROR")) {
				return 88;
			}

			if (resutlMap != null && WeixinConstant.FAIL.equals(resutlMap.get("result_code"))) {
				log.error("企业付款失败：orderId=" + orderId + "，失败原因：return_msg=" + resutlMap.get("return_msg") + "，"
						+ objInfo.toString());
				// 真实姓名不一致
				// 非实名用户账号不可发放
				// 用户账号被冻结，无法付款
				if (StrUtil.null2Str(resutlMap.get("err_code")).equals("NAME_MISMATCH")
						|| StrUtil.null2Str(resutlMap.get("err_code")).equals("V2_ACCOUNT_SIMPLE_BAN")
						|| StrUtil.null2Str(resutlMap.get("err_code")).equals("NO_AUTH")) {
					if (objInfo != null) {
						objInfo.setFailErrorMsg(
								resutlMap.get("err_code").toString() + ":" + resutlMap.get("err_code_des").toString());
						objInfo.setOrderStatusName("提现失败，金币已返还--" + resutlMap.get("err_code_des").toString());
						return 4;
					}

					// 参数错误
				} else if (StrUtil.null2Str(resutlMap.get("err_code")).equals("PARAM_ERROR")) {
					if (objInfo != null) {
						objInfo.setFailErrorMsg(
								resutlMap.get("err_code").toString() + ":" + resutlMap.get("err_code_des").toString());
						objInfo.setOrderStatusName("提现失败，金币已返还--" + "支付失败，请重新申请");
						return 4;
					}
				}

				String return_msg = resutlMap.get("return_msg").toString();
				if (return_msg.equals("openid与商户appid不匹配")) {
					log.error("提现失败【准备重试】，openid与商户appid不匹配。" + objInfo.toString());
					return 44;
				}
				return 55;
			}

			if (WeixinConstant.SUCCESS.equalsIgnoreCase(resutlMap.get("result_code").toString())) {
				Map<String, Object> map = buildClientJson(resutlMap);
				log.info("企业付款成功：" + map.toString());
				return 0;
			}

		} catch (Exception e) {
			log.error("企业付款异常：" + e.getMessage(), e);
			return 5;
		}
		return 1;
	}

	/**
	 * 查询指定订单id状态
	 */
	public static int doEquery(String appId, String outOrderNo, String projectFlag) {
		if (Strings.isNullOrEmpty(outOrderNo)) {
			return 1;
		}
		try {
			// 组装查询参数
			SortedMap<String, Object> params = buildQueryParams(appId, outOrderNo, projectFlag);
			String requestXML = PayCommonUtil.getRequestXml(params);// 生成xml格式字符串
			// 带上post请求支付查询接口
			String responseStr = FsClientWithCertSSL.doPost(ConfigUtil.PROMOTION_QUERY_URL, requestXML, projectFlag);
			SortedMap<String, Object> responseMap = XMLUtil.doXMLParse(responseStr);// 解析响应xml格式字符串
			log.info("企业付款查询=> " + responseMap.toString());

			// 校验响应结果return_code
			if (WeixinConstant.FAIL.equalsIgnoreCase(responseMap.get("return_code").toString())) {
				log.error("企业支付调用失败");
				return 2;
			}

			// 校验业务结果result_code
			if (WeixinConstant.FAIL.equalsIgnoreCase(responseMap.get("result_code").toString())) {
				if ("NOT_FOUND".equalsIgnoreCase(responseMap.get("err_code").toString())) {
					log.info("指定单号数据不存在，放行打款...");
					return 4;
				}
				return 3;
			}

			if (StrUtil.null2Str(responseMap.get("err_code")).equals("SYSTEMERROR")
					&& StrUtil.null2Str(responseMap.get("status")).equals("PROCESSING")) {
				// 扣款消耗失败，退回提现
				if (StrUtil.null2Str(responseMap.get("reason")).equals("consumefund failed.")
						&& StrUtil.null2Str(responseMap.get("return_msg")).equals("consumefund failed.")) {
					log.error("扣款消耗失败，consumefund failed，退回提现");
					return 99;
				}
				return 77;
			}

			if (StrUtil.null2Str(responseMap.get("err_code")).equals("SYSTEMERROR")) {
				return 88;
			}

			if (WeixinConstant.SUCCESS.equalsIgnoreCase(responseMap.get("result_code").toString())) {
				log.error("已打款，不能重复打款");
				return 0;
			}

			// 组装响应数据
			Map<String, Object> resultMap = PayServiceUtils.buildResponse(responseMap);
			log.info("企业付款查询结果：" + resultMap.toString());
			return -1;

		} catch (Exception e) {
			log.error("付款查询异常：" + e.getMessage(), e);
			return 5;
		}
	}

	/**
	 * 组装响应数据
	 * 
	 * @param resutlMap 付款响应结果
	 * @return
	 */
	private static Map<String, Object> buildClientJson(Map<String, Object> resutlMap) {

		if (resutlMap == null || resutlMap.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, Object> returnMap = ImmutableMap.<String, Object>builder()
				.put("trade_no", resutlMap.get("partner_trade_no")).put("payment_no", resutlMap.get("payment_no"))
				.put("payment_time", resutlMap.get("payment_time")).put("result_msg", resutlMap.get("result_code"))
				.build();
		return returnMap;
	}

	/**
	 * 组合sign签名参数
	 * 
	 * @return
	 */
	private static SortedMap<String, Object> getSignParams(String appId, String tradeNo, String openId, int amount,
			String ip, String re_user_name, String projectFlag, String appDesc) {
//		String appId = WeixinPayConfig.APPID;
		String mchId = WeixinPayConfig.MCHID;
		Map<String, Object> oparams = ImmutableMap.<String, Object>builder().put("mch_appid", appId)
//				.put("desc", WeixinConstant.EPAY_DESC)
				.put("desc", appDesc)
				// 企业付款描述信息
				.put("mchid", mchId)
				// 商户号
				.put("nonce_str", PayCommonUtil.CreateNoncestr())
				// 16随机字符串(大小写字母加数字)
				.put("device_info", PayCommonUtil.createConceStr(32).toUpperCase())// 设备号 暂时写死
				.put("partner_trade_no", tradeNo)// 商户订单号
				.put("openid", openId)// 用户openid 注意:微信的openid
				.put("check_name", "NO_CHECK")// 不校验真实姓名
//				.put("check_name", "FORCE_CHECK")// 校验真实姓名
//				.put("re_user_name", re_user_name)// 校验真实姓名
				.put("amount", amount)// 金额
				.put("spbill_create_ip", ip)// ip地址
				.build();
		return MapUtils.sortMap(oparams);
	}

	/**
	 * 封装查询结果数据
	 * 
	 * @param responseMap 查询结果
	 * @return
	 */
	private static Map<String, Object> buildResponse(SortedMap<String, Object> responseMap) {
		Map<String, Object> returnMap = ImmutableMap.<String, Object>builder()
				.put("trade_no", responseMap.get("partner_trade_no")).put("payment_no", responseMap.get("detail_id"))
				.put("payment_account", responseMap.get("payment_amount"))
				.put("transfer_time", responseMap.get("transfer_time"))
				.put("result_code", responseMap.get("result_code")).build();
		return returnMap;
	}

	/**
	 * 组装查询参数
	 * 
	 * @param outTradeNo
	 * @return
	 */
	private static SortedMap<String, Object> buildQueryParams(String appId, String outTradeNo, String projectFlag) {
		String mchId = WeixinPayConfig.MCHID;
		// 组装查询参数- 可以使用treemap
		Map<String, Object> queryParams = ImmutableMap.<String, Object>builder().put("appid", appId)// 商户号的appid
				.put("mch_id", mchId)// 商户号
				.put("nonce_str", PayCommonUtil.CreateNoncestr())// 16随机字符串(大小写字母加数字)
				.put("partner_trade_no", outTradeNo)// 商户订单号
				.build();
		// key ASCII 排序
		SortedMap<String, Object> sortMap = MapUtils.sortMap(queryParams);
		// MD5签名
		String createSign = PayCommonUtil.createSignPublic(Charsets.UTF_8.toString(), sortMap, projectFlag);
		sortMap.put("sign", createSign);
		return sortMap;
	}
}
