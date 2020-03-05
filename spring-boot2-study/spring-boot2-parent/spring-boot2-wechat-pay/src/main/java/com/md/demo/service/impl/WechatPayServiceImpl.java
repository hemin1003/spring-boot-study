package com.md.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.md.demo.pay.utils.StrUtil;
import com.md.demo.pay.utils.variation.PayServiceUtils;
import com.md.demo.pay.utils.vo.PayWithdrawHis;
import com.md.demo.service.IWechatPayService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author minbo
 */
@Service
@Slf4j
public class WechatPayServiceImpl implements IWechatPayService {

	/**
	 * 悦头条-新版-微信批量打款
	 */
	@Override
	public void wechatBatchPay() {

		// TODO 这里可从DB中获得订单数据
//		String startDate = DateUtil.getCurrentDate(-7);
//		String endDate = DateUtil.getCurrentDateTimeHours(-1);
//		List<PayWithdrawHis> list =
//		this.withdrawHisService.listPassWithdrawData(startDate, endDate);

		List<PayWithdrawHis> list = new ArrayList<PayWithdrawHis>();
		loopFlag: for (PayWithdrawHis objInfo : list) {
			log.info("---------------------------------------------");
			log.info("--------------------start--------------------");
			try {

				// 这里的openId，是当前应用或公众号下的openId
				String openId = StrUtil.null2Str(objInfo.getWechatOpenId());

				String ip = "0.0.0.0";
				// 提现金币，微信官方后台是分为单位，则要乘以100
				String amount = String.valueOf(Integer.valueOf(objInfo.getIncome()) * 100);
				// 订单ID
				String orderId = objInfo.getOrderId();

//				// 获得appId的配置，可以把支付信息等做成DB可配置项
//				PayAppConfig sdkPayAppConfig = this.sdkPayAppConfigService.getById(objInfo.getAppId());
//				String appIdName = sdkPayAppConfig.getProductName() + "的红包";
//				String wxAppId = sdkPayAppConfig.getWxAppId();

				// 企业付款描述
				String appIdName = "填入账信息详情展示"; // 例如：XXX产品的红包
				String wxAppId = "微信后台的appId";

				// 1. 先查询订单的状态
				int flag = PayServiceUtils.doEquery(wxAppId, orderId, null);
				if (flag == 99) {
					log.warn("查询，扣款消耗失败，退回打款");

					// TODO 扣款消耗失败处理
//					objInfo.setFailErrorMsg("微信官方后台-扣款消耗失败");
//					objInfo.setOrderStatusName("提现失败，金币已返还--提现失败，请重新申请");
//					this.updateWithdrawHisForfail(objInfo);
					continue;

				}

				if (flag == 88) {
					log.error("查询，后台企业微信账户系统异常，停止打款", new RuntimeException("微信账户异常，停止此次打款"));
					break loopFlag;

				}

				if (flag == 77) {
					log.error("查询，后台企业微信账户系统订单正在处理中", new RuntimeException("微信账户订单正在处理中，跳过处理"));
					continue;

				}

				if (flag == 0) {
					log.info("之前已经打款成功，直接更新状态即可。");
					// TODO 已打款成功，不重复打款，直接更新状态处理
//					this.updateWithdrawHis(objInfo);
					continue;
				}

				// 不存在此订单数据，真正开始打款
				if (flag == 4) {
					log.info("开始真正打款，然后更新订单状态...");
					int result = PayServiceUtils.doEpay(wxAppId, openId, ip, amount, orderId, null, objInfo, null,
							appIdName);

					if (result == 99) {
						log.error("打款，后台企业微信账户支付余额不足，停止打款", new RuntimeException("余额不足，停止此次打款"));
						break loopFlag;
					}

					if (result == 88) {
						log.error("打款，后台企业微信账户系统异常，停止打款。" + objInfo.toString(), new RuntimeException("微信账户异常，停止此次打款"));
						break loopFlag;
					}

					if (result == 77) {
						log.error("打款速度过快，微信账户处理繁忙，休眠十秒。" + objInfo.toString());
						Thread.sleep(10000);
						continue;
					}

					if (result == 66) {
						log.error("该用户今日付款次数超过限制,如有需要请登录微信支付商户平台更改API安全配置。",
								new RuntimeException("该用户今日付款次数超过限制，跳过该用户的打款"));
						continue;
					}

					if (result == 55) {
						log.error("打款报错【微信】，请检查。" + objInfo.toString(), new RuntimeException("打款报错，跳过该用户打款"));
						continue;
					}

					// 最后结果处理
					if (result == 0) {
						// TODO 打款成功处理
//						this.updateWithdrawHis(objInfo);

					} else if (result == 4) {
						// TODO 打款失败处理
//						this.updateWithdrawHisForfail(objInfo);
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
//	 * @param PayWithdrawHis
//	 */
//	private void updateWithdrawHis(PayWithdrawHis entity) {
//		entity.setOrderStatus(2);
//		entity.setOrderStatusName("提现成功");
//		entity.setUpdatedDate(new Date());
//		boolean status = this.withdrawHisService.updateById(entity);
//		if (status) {
//			log.info("状态更新成功 for 提现成功");
//
//		} else {
//			log.error("状态更新失败 for 提现成功");
//		}
//	}

//	/**
//	 * 提现失败处理
//	 * 
//	 * @param PayWithdrawHis
//	 */
//	private boolean updateWithdrawHisForfail(PayWithdrawHis entity) {
//		Date cTime = new Date();
//
//		PayUserRedpacketInfo sdkPayUserRedpacketInfo = this.sdkPayUserRedpacketInfoService
//				.getById(entity.getSysUserId());
//		if (sdkPayUserRedpacketInfo == null) {
//			log.error("用户数据不存在，不允许返还金币，跳过处理。entity={}", JSON.toJSONString(entity));
//			return false;
//		}
//
//		// 先查订单是否已发放过（奖励历史）
//		QueryWrapper<PayAwardHis> queryWrapper = new QueryWrapper<PayAwardHis>();
//		queryWrapper.eq("busId", entity.getOrderId());
//
//		List<PayAwardHis> listOfPayAwardHis = this.sdkPayAwardHisService.list(queryWrapper);
//		if (listOfPayAwardHis != null && listOfPayAwardHis.size() > 0) {
//
//			log.error("此订单已返还了金币数据，不允许再次返还金币，跳过处理。entity={}", JSON.toJSONString(entity));
//
//			// 直接更新订单状态
//			this.updateOrderStatusForFailById(entity, cTime);
//
//			return false;
//		}
//
//		// 1. 金币需返还
//		Long oldVersion = sdkPayUserRedpacketInfo.getVersion();
//		// 旧值
//		Long oldLeftGold = Long.valueOf(sdkPayUserRedpacketInfo.getLeftGold());
//		// 新值
//		Long newLeftGold = oldLeftGold + Math.abs(Long.valueOf(entity.getGold()));
//
//		// 更新用户余额
//		sdkPayUserRedpacketInfo.setLeftGold(String.valueOf(newLeftGold));
//		sdkPayUserRedpacketInfo.setVersion(sdkPayUserRedpacketInfo.getVersion() + 1);
//		sdkPayUserRedpacketInfo.setUpdatedDate(cTime);
//
//		// 更新条件
//		UpdateWrapper<PayUserRedpacketInfo> updateWrapper = new UpdateWrapper<PayUserRedpacketInfo>();
//		updateWrapper.eq("sysUserId", entity.getSysUserId());
//		updateWrapper.eq("version", oldVersion);
//		boolean result = this.sdkPayUserRedpacketInfoService.update(sdkPayUserRedpacketInfo, updateWrapper);
//		if (!result) {
//			log.error("更新用户余额失败，跳过处理。sdkPayUserRedpacketInfo={}", JSON.toJSONString(sdkPayUserRedpacketInfo));
//			return false;
//		}
//		log.info("金币返还成功, sysUserId={}, oldLeftGold={}, 要返还的gold={}, newLeftGold={}, orderId={}", entity.getSysUserId(),
//				oldLeftGold, entity.getGold(), newLeftGold, entity.getOrderId());
//
//		// 2. 记录返还奖励历史
//		boolean result2 = this.addPayAwardHis(sdkPayUserRedpacketInfo, entity, cTime);
//		if (result2) {
//			// 3. 更新订单状态
//			this.updateOrderStatusForFailById(entity, cTime);
//			return true;
//
//		} else {
//			return false;
//		}
//	}

//	/**
//	 * 记录返还奖励历史
//	 * 
//	 * @param sdkPayUserRedpacketInfo
//	 * @param entity
//	 * @param cTime
//	 * @return
//	 */
//	private boolean addPayAwardHis(PayUserRedpacketInfo sdkPayUserRedpacketInfo, PayWithdrawHis entity,
//			Date cTime) {
//		PayAwardHis sdkPayAwardHis = new PayAwardHis();
//		sdkPayAwardHis.setSysUserId(sdkPayUserRedpacketInfo.getSysUserId());
//		sdkPayAwardHis.setAppId(sdkPayUserRedpacketInfo.getAppId());
//		sdkPayAwardHis.setAppUserId(sdkPayUserRedpacketInfo.getAppUserId());
//		sdkPayAwardHis.setType(99);
//		sdkPayAwardHis.setAwardValue(entity.getGold());
//		sdkPayAwardHis.setBusId(entity.getOrderId());
//		sdkPayAwardHis.setCreatedDate(cTime);
//		boolean result2 = this.sdkPayAwardHisService.save(sdkPayAwardHis);
//		if (!result2) {
//			log.error("记录返还奖励历史失败，跳过处理。sdkPayAwardHis={}", JSON.toJSONString(sdkPayAwardHis));
//			return false;
//		}
//		return true;
//	}

//	/**
//	 * 更新订单状态(失败)
//	 * 
//	 * @param entity
//	 * @param cTime
//	 */
//	private void updateOrderStatusForFailById(PayWithdrawHis entity, Date cTime) {
//		entity.setOrderStatus(4);
//		entity.setUpdatedDate(cTime);
//		boolean status = this.withdrawHisService.updateById(entity);
//		if (status) {
//			log.info("提现记录状态(for 提现失败)，更新success。entity={}", JSON.toJSONString(entity));
//		} else {
//			log.error("提现记录状态(fot 提现失败)，更新fail。entity={}", JSON.toJSONString(entity));
//		}
//	}

}