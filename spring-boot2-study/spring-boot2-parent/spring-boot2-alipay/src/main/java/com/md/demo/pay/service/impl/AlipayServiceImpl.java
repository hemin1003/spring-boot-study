package com.md.demo.pay.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.md.demo.pay.service.IAlipayService;
import com.md.demo.pay.util.AliPayServiceUtils;
import com.md.demo.pay.utils.vo.PayWithdrawHis;
import com.md.demo.util.StrUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 支付宝自动打款
 * 
 * @author Minbo
 */
@Service
@Slf4j
public class AlipayServiceImpl implements IAlipayService {

	/**
	 * 支付宝批量打款
	 */
	@Override
	public void alipayBatchPay() {

		// TODO 这里从DB中获得订单数据
		List<PayWithdrawHis> list = new ArrayList<PayWithdrawHis>();

		for (PayWithdrawHis objInfo : list) {
			log.info("---------------------------------------------");
			log.info("--------------------start--------------------");

			try {
				String name = StrUtil.null2Str(objInfo.getName());
				String amount = objInfo.getIncome();
				String orderId = objInfo.getOrderId();
				String account = StrUtil.null2Str(objInfo.getAccount());

				log.info("打款订单：" + objInfo.toString());

				// 1. 先查询此订单状态
				int flag = AliPayServiceUtils.query(orderId);
				if (flag == 0) {
					log.info("之前已经打款成功，直接更新状态即可。");

					// TODO 直接更新订单桩体
//					this.updateHttWithdrawHis(objInfo);

					// 2. 不存在此订单数据，则打款
				} else if (flag == 4) {
					log.info("开始真正打款，然后更新状态。");
					int result = AliPayServiceUtils.transfer(orderId, account, amount, name, objInfo);

					if (result == 99) {
						log.error("打款，后台企业支付宝账户支付余额不足，停止打款", new RuntimeException("余额不足，停止此次打款"));
						break;
					}
					if (result == 88) {
						log.error("打款，后台企业支付宝账户系统异常，停止打款。" + objInfo.toString(),
								new RuntimeException("支付宝账户异常，停止此次打款"));
						break;
					}
					if (result == 77) {
						log.error("支付宝账户系统繁忙，休眠十秒。" + objInfo.toString());
						Thread.sleep(10000);
					}
					if (result == 55) {
						log.error("打款报错【支付宝】，请检查。" + objInfo.toString(), new RuntimeException("打款报错，跳过该用户打款"));
						continue;
					}

					if (result == 0) {
						// TODO 打款成功处理
//						this.updateHttWithdrawHis(objInfo);

					} else if (result == 4) {
						// TODO 打款失败处理
//						this.httWithdrawHisService.failProcess(objInfo);
					}
				}
			} catch (Exception e) {
				log.error("提现记录异常：orderId=" + objInfo.getOrderId() + ", msg=" + e.getMessage(), e);
			}
			log.info("--------------------end--------------------");
			log.info("---------------------------------------------");
		}
	}

//	/**
//	 * 更新提现记录-‘提现成功’状态
//	 * 
//	 * @param httWithdrawHisVo
//	 */
//	private void updateHttWithdrawHis(PayWithdrawHis httWithdrawHisVo) {
//		httWithdrawHisVo.setStatus("提现成功");
//		httWithdrawHisVo.setStatusType(2);
//		httWithdrawHisVo.setUpdateDate(DateUtil.getCurrentLongDateTime());
//		boolean status = this.httWithdrawHisService.updateHttWithdrawHis(httWithdrawHisVo);
//		if (status) {
//			log.info("状态更新成功 for 提现成功");
//		} else {
//			log.info("状态更新失败 for 提现成功");
//		}
//	}
}