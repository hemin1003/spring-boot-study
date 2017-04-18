package com.minbo.springdemo.web.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/kafka")
public class MsgProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("/producer")
	public String send() {
		kafkaTemplate.send("my-replicated-topic", "hello kafka 111");
		kafkaTemplate.send("my-replicated-topic", "hello kafka 222");

		kafkaTemplate.metrics();

		kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
			@Override
			public Object doInKafka(Producer<String, String> producer) {
				// 这里可以编写kafka原生的api操作
				return null;
			}
		});

		// 消息发送的监听器，用于回调返回信息
		kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
			@Override
			public void onSuccess(String topic, Integer partition, String key, String value,
					RecordMetadata recordMetadata) {

			}

			@Override
			public void onError(String topic, Integer partition, String key, String value, Exception exception) {

			}

			@Override
			public boolean isInterestedInSuccess() {
				return false;
			}
		});
		
		return "success";
	}
}
