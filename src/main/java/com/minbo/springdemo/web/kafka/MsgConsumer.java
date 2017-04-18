package com.minbo.springdemo.web.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/kafka")
public class MsgConsumer {
	
	@KafkaListener(topics = { "my-replicated-topic", "my-replicated-topic2" })
	@RequestMapping("/consumer")
	public String processMessage(String content) {
		System.out.println("服务消费者, content=" + content);
		return "consumer";
	}
	
}
