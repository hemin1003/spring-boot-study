package com.md.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.md.demo.service.IWechatPayService;

@Component
public class ScheduledTasks {

	protected static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	private IWechatPayService wechatEpayService;

	/**
	 * 每60秒执行一次，从DB中获得订单数据
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 60000)
	public void httTaskOfWechatPay() {
		logger.info("------------------------------------------------");
		logger.info("============微信打款任务，start===================");

		// TODO 调用打款服务
//		this.wechatEpayService.wechatBatchPayHtt();

		logger.info("============微信打款任务，end===================");
		logger.info("------------------------------------------------");
	}
}