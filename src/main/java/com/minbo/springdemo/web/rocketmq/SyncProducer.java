package com.minbo.springdemo.web.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {

	public static void main(String[] args) throws Exception {
		
		try {
			// Instantiate with a producer group name.
			DefaultMQProducer producer = new DefaultMQProducer("my_rocket_mq");
			
			// Specify name server addresses.
			producer.setNamesrvAddr("localhost:9876");
			
			// Launch the instance.
			producer.start();
			
			for (int i = 0; i < 10; i++) {
				// Create a message instance, specifying topic, tag and message body.
				Message msgs = new Message("TopicTest" /* Topic */, "TagA" /* Tag */,
						("Hello My RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
				);
				// Call send message to deliver message to one of brokers.
				SendResult sendResult = producer.send(msgs, 86400);
				System.out.printf("%s%n", sendResult);
			}
			
			// Shut down once the producer instance is not longer in use.
			producer.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
