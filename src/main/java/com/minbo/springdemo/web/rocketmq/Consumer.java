package com.minbo.springdemo.web.rocketmq;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class Consumer {
	public static void main(String[] args) throws InterruptedException, MQClientException {

		// Instantiate with specified consumer group name.
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my_rocket_mq");

		// Specify name server addresses.
		consumer.setNamesrvAddr("localhost:9876");

		// Subscribe one more more topics to consume.
		consumer.subscribe("TopicTest", "*");
		
		// Register callback to execute on arrival of messages fetched from brokers.
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				//System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
				for (MessageExt msg : msgs) {
					System.out.println("消费者消费数据：" + new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		// Launch the consumer instance.
		consumer.start();

		System.out.printf("Consumer Started.%n");
	}
}
