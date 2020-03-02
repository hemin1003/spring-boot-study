package com.md.demo.pay.task;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.md.demo.pay.service.IAlipayService;

@Component
public class ScheduledTasks {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private IAlipayService alipayService;

	/**
	 * 每60秒执行一次，从DB中获得订单数据
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 60000)
	public void httTaskOfWechatPay() {
		if (!isGoPayment()) {
			return;
		}
		
		logger.info("------------------------------------------------");
		logger.info("============支付宝打款任务，start===================");

		// 调用打款服务
//		this.alipayService.alipayBatchPay();

		logger.info("============支付宝打款任务，end===================");
		logger.info("------------------------------------------------");
	}

	/**
	 * 是否允许自动打款-判断时间区间
	 * 
	 * @return
	 */
	public boolean isGoPayment() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		logger.info("当前小时hour=" + hour);

		if (hour >= 0 && hour <= 8) {
			logger.info("0点到8点期间，不进行打款，停止打款任");
			return false;
		}
		return true;
	}

}