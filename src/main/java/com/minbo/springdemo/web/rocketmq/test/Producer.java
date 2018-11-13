package com.minbo.springdemo.web.rocketmq.test;

import java.io.Serializable;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import com.alibaba.fastjson.JSON;

public class Producer {
	public static void main(String[] args) throws MQClientException {
		DefaultMQProducer producer = new DefaultMQProducer("test-group");
		producer.setNamesrvAddr("localhost:9876");
		producer.setInstanceName("rmq-instance");
		producer.start();
		try {
			for (int j = 1; j < 100; j++) {
				for (int i = 0; i < 5000; i++) {
					User user = new User();
					user.setLoginName("abc" + i);
					user.setPwd(String.valueOf(i));
					Message message = new Message("log-topic", "user-tag", JSON.toJSONString(user).getBytes());
					System.out.println("生产者发送消息：" + JSON.toJSONString(user));
					producer.send(message, 86400);
				}
				Thread.sleep(300);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		producer.shutdown();
	}

	/**
	 * 发送用户消息
	 */
	static class User implements Serializable {
		private static final long serialVersionUID = 1L;

		private String loginName;
		private String pwd;

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	}
}
