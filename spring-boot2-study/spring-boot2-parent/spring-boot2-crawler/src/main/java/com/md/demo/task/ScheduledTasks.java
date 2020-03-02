package com.md.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.md.demo.crawler.test.MyPageProcessor;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Spider;

@Component
@Slf4j
public class ScheduledTasks {

	/**
	 * 每30秒执行一次
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 30000)
	public void httTaskOfWechatPay() {
		log.info("------------------------------------------------");
		log.info("============爬虫数据任务，start===================");

		// TODO 在这里，你可以把爬虫做成执行计划任务，或者动态执行计划（通过db配置，或quartz框架，或XXL-job任务调度计划框架）

		log.info("开始爬虫数据...");
		log.info("处理处理...可以入库db，或存入es中");

		// 爬今日头条/热点数据url
		Spider.create(new MyPageProcessor()).addUrl(
				"https://m.toutiao.com/list/?tag=news_hot&ac=wap&count=20&format=json_raw&as=A1551E75BC4CF9C&cp=5E5C3CEFB9DCAE1&max_behot_time=1583123351&_signature=CXZIuwAAVy.txcPhjSEcTwl2SK&i=1583123351")
				.thread(1).run();

		log.info("爬虫结束...done");

		log.info("============爬虫数据任务，end===================");
		log.info("------------------------------------------------");
	}

}