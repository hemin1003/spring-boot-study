package com.minbo.springdemo.web.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class AsyncProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer("my_rocket_mq");
		// Specify name server addresses.
		producer.setNamesrvAddr("localhost:9876");
		// Launch the instance.
		producer.start();
		producer.setRetryTimesWhenSendAsyncFailed(0);
		for (int i = 0; i < 100; i++) {
			final int index = i;
			// Create a message instance, specifying topic, tag and message body.
			Message msg = new Message("TopicTest", "TagA", "OrderID188",
					"Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
			producer.send(msg, new SendCallback() {
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
				}

				@Override
				public void onException(Throwable e) {
					System.out.printf("%-10d Exception %s %n", index, e);
					e.printStackTrace();
				}
			}, 86400);
		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}
}
