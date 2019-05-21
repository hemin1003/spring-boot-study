package com.md.demo.consumer;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.md.demo.MyTopics;
import com.md.demo.vo.OrderPaidEvent;

/**
 * 消费者，消费消息
 * 
 * 直接run运行即可
 * 
 * @author Minbo
 *
 */
public class ConsumerApplication {

	protected static Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

	public static void main(String[] args) throws InterruptedException, MQClientException {

		// 指定消费组名
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-mq-group");

		// 指定服务器地址名
		consumer.setNamesrvAddr("localhost:9876");

		// 订阅一个或多个主题
		consumer.subscribe(MyTopics.TOPIC1, "*");

		// 注册回调
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					logger.info("入口，消息tag=" + msg.getTags());

					// 区分不同Tag，不同处理方式
					switch (msg.getTags()) {
					
						// 1. 普通文本消息
						case MyTopics.TAG1:
							logger.info("消息tag=" + msg.getTags() + "，消费者，消费数据【普通文本消息】：msg=" + new String(msg.getBody()));
							break;
							
						// 2. 对象数据消息
						case MyTopics.TAG2:
							Gson gson = new Gson();
							OrderPaidEvent obj = gson.fromJson(new String(msg.getBody()), OrderPaidEvent.class);
							logger.info("消息tag=" + msg.getTags() + "，消费者，消费数据【对象数据】：order=" + obj.toString());
							break;
							
					}
				}

				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		// 启动消费者实例
		consumer.start();

		logger.info("Consumer Started.");
	}

}
