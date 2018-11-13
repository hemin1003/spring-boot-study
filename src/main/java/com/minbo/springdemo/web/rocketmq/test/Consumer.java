package com.minbo.springdemo.web.rocketmq.test;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class Consumer {
	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-group");

		consumer.setNamesrvAddr("localhost:9876");
		consumer.setInstanceName("rmq-instance");
		consumer.subscribe("log-topic", "user-tag");

		consumer.registerMessageListener(new MessageListenerConcurrently() {

			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					System.out.println("消费者消费数据：" + new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.out.printf("Consumer Started");
	}
}
