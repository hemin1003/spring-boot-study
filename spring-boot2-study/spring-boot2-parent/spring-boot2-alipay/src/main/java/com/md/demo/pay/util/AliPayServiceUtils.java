package com.md.demo.pay.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.md.demo.pay.utils.vo.PayWithdrawHis;
import com.md.demo.util.StrUtil;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * 支付服务工具类
 * 
 * @author Minbo
 *
 */
@Slf4j
public class AliPayServiceUtils {

	// TODO 填写支付宝接入信息
	private static final String APP_ID = "xxxxxx";
	private static final String APP_PRIVATE_KEY = "xxxxxx";
	private static final String ALIPAY_PUBLIC_KEY = "xxxxxx";

	private static AlipayClient alipayClient;

	private static final String payer_show_name = "xxx企业账户";
	private static final String remark = "提现转账测试";

	static {
		alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json",
				"UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
	}

	/**
	 * 转账打款
	 */
	public static int transfer(String out_biz_no, String payee_account, String amount, String payee_real_name,
			PayWithdrawHis objInfo) {
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		request.setBizContent(
				"{" + "    \"out_biz_no\":\"" + out_biz_no + "\"," + "    \"payee_type\":\"ALIPAY_LOGONID\","
						+ "    \"payee_account\":\"" + payee_account + "\"," + "    \"amount\":\"" + amount + "\","
						+ "    \"payer_show_name\":\"" + payer_show_name + "\"," + "    \"payee_real_name\":\""
						+ payee_real_name + "\"," + "    \"remark\":\"" + remark + "\"," + "  }");
		try {
			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
			String json = response.getBody();
			log.info("打款操作结果json=" + json);

			JSONObject obj = JSONObject.fromObject(json);
			JSONObject objResp = JSONObject.fromObject(obj.get("alipay_fund_trans_toaccount_transfer_response"));
			if (StrUtil.null2Str(objResp.get("code")).equals("10000")) {
				log.info("打款成功");
				return 0;

			} else {
				if (StrUtil.null2Str(objResp.get("sub_code")).equals("PAYER_BALANCE_NOT_ENOUGH")) {
					return 99;
				}
				if (StrUtil.null2Str(objResp.get("sub_code")).equals("aop.SYSTEM_ERROR")) {
					return 88;
				}
				if (StrUtil.null2Str(objResp.get("sub_code")).equals("isp.unknow-error")
						&& StrUtil.null2Str(objResp.get("sub_msg")).equals("系统繁忙")) {
					return 77;
				}

				if (objInfo != null) {
					// 支付宝账号和姓名不匹配，请确认姓名是否正确
					// 收款账号不存在
					if (StrUtil.null2Str(objResp.get("sub_code")).equals("PAYEE_USER_INFO_ERROR")
							|| StrUtil.null2Str(objResp.get("sub_code")).equals("PAYEE_NOT_EXIST")) {
						objInfo.setFailErrorMsg(StrUtil.null2Str(objResp.get("sub_code")) + ":"
								+ StrUtil.null2Str(objResp.get("sub_msg")));
						objInfo.setOrderStatusName("提现失败，金币已返还-" + StrUtil.null2Str(objResp.get("sub_msg")));

						log.error("打款失败。objResp=" + objResp.toString() + "，httWithdrawHisVo--->" + objInfo.toString());
						return 4;
					}
				}
				return 55;
			}

		} catch (AlipayApiException e) {
			log.error("转账异常：" + e.getMessage(), e);
			return 2;
		}
	}

	/**
	 * 订单查询
	 */
	public static int query(String out_biz_no) {
		AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
		request.setBizContent("{" + "    \"out_biz_no\":\"" + out_biz_no + "\" " + "}");

		try {
			AlipayFundTransOrderQueryResponse response = alipayClient.execute(request);
			String json = response.getBody();
			log.info("查询打款结果json=" + json);

			JSONObject obj = JSONObject.fromObject(json);
			JSONObject objResp = JSONObject.fromObject(obj.get("alipay_fund_trans_order_query_response"));

			if (StrUtil.null2Str(objResp.get("code")).equals("10000")
					&& StrUtil.null2Str(objResp.get("msg")).equals("Success")) {
				log.error("订单已打款，不能重复打款。out_biz_no=" + out_biz_no + "，json=" + json);
				return 0;
			}
			if (StrUtil.null2Str(objResp.get("code")).equals("40004")
					&& StrUtil.null2Str(objResp.get("sub_code")).equals("ORDER_NOT_EXIST")) {
				log.info("订单不存在，可以打款");
				return 4;

			} else {
				return 1;
			}
		} catch (AlipayApiException e) {
			log.error("查询异常：" + e.getMessage(), e);
			return 3;
		}
	}
}
