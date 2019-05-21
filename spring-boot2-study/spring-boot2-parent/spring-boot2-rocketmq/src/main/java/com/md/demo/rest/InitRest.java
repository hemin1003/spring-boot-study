package com.md.demo.rest;

import java.math.BigDecimal;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.MyTopics;
import com.md.demo.vo.OrderPaidEvent;
import com.zc.smartcity.rocketmq.core.RocketMQTemplate;

/**
 * @author Minbo
 */
@RestController
public class InitRest {

	protected static Logger logger = LoggerFactory.getLogger(InitRest.class);

	@Resource
	private RocketMQTemplate rocketMQTemplate;

	@GetMapping("/hello")
	public String hello() {
		return "Hello greetings from spring-boot2-rocketmq";
	}

	/**
	 * 发送普通文本消息
	 * http://localhost:9090/sendMsgText
	 * @return
	 */
	@RequestMapping("/sendMsgText")
	public String sendMsgText() {
		// 发送普通文本消息
		String msg = "Hello，World，spring-boot2-rocketmq! flag=" + new Random().nextInt(100);
		String destination1 = MyTopics.TOPIC1 + ":" + MyTopics.TAG1;
		rocketMQTemplate.convertAndSend(destination1, msg);
		logger.info("sendMsg success。发送普通文本消息 msg=" + msg);

		return "success for text";
	}

	/**
	 * 发送对象数据消息
	 * http://localhost:9090/sendMsgObj
	 * @return
	 */
	@RequestMapping("/sendMsgObj")
	public String sendMsgObj() {
		// 发送对象数据消息
		OrderPaidEvent order = new OrderPaidEvent("T_001_ID_" + new Random().nextInt(100), new BigDecimal("88.00"));
		String destination2 = MyTopics.TOPIC1 + ":" + MyTopics.TAG2;
		rocketMQTemplate.convertAndSend(destination2, order);
		logger.info("sendMsg success。发送对象消息 order=" + order.toString());

		return "success for obj";
	}

	/*
	 * RocketMQ的最佳实践中推荐：一个应用尽可能用一个Topic，消息子类型用tags来标识，tags可以由应用自由设置。
	 * 在使用rocketMQTemplate发送消息时，通过设置发送方法的destination参数来设置消息的目的地，
	 * destination的格式为topicName:tagName，:前面表示topic的名称，后面表示tags名称。
	 * 
	 * 注意: tags从命名来看像是一个复数，但发送消息时，目的地只能指定一个topic下的一个tag，不能指定多个。
	 */
}