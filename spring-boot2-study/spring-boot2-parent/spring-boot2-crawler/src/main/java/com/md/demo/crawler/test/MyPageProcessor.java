package com.md.demo.crawler.test;

import java.util.List;

import org.nlpcn.commons.lang.finger.SimHashService;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

/**
 * 官方地址：http://webmagic.io/
 * 
 * WebMagic是一个简单灵活的Java爬虫框架。基于WebMagic，你可以快速开发出一个高效、易维护的爬虫。
 * 
 * @author Minbo
 *
 */
@Slf4j
public class MyPageProcessor implements PageProcessor {

	private Site site = Site.me();

	@Override
	public void process(Page page) {
		// 这里使用了jsonPath，因为今日头条返回的是json数据格式。如果是html内容，则使用xpath

		// 第一层解析
		String json = page.getRawText();
		List<String> data = new JsonPathSelector("$.data").selectList(json);
		// 第二层解析
		for (int i = 0; i < data.size(); i++) {
			String result = data.get(i);
			// 文章ID
			String item_id = new JsonPathSelector("$.item_id").select(result);
			// 文章标题
			String title = new JsonPathSelector("$.title").select(result);
			// 是否有图
			String has_image = new JsonPathSelector("$.has_image").select(result);
			// 文章来源
			String source = new JsonPathSelector("$.source").select(result);
			// 文章时间
			String datetime = new JsonPathSelector("$.datetime").select(result);
			// 一般是根据文章详细内容生成内容指纹id，实现文章去重，这里只是举例说明拿了文章标题
			// 更多阅读我的博客文章：https://blog.csdn.net/hemin1003/article/details/82819489（【应用算法】信息流-推荐系统的去重策略）
			String fPrintId = String.valueOf(new SimHashService().fingerprint(title));

			log.info("");
			log.info("---------->>> 提取文章主信息：");
			log.info("文章ID item_id=" + item_id);
			log.info("文章标题 title=" + title);
			log.info("是否有图 has_image=" + has_image);
			log.info("文章来源 source=" + source);
			log.info("文章时间 datetime=" + datetime);
			log.info("内容指纹id fPrintId=" + fPrintId);

			// webmagic官方还有很多案例，更多内容自行参考学习了，例如配置代理，自带url去重、网页去重等功能
			// 官方文档地址：http://webmagic.io/docs/zh/
		}
	}

	public Site getSite() {
		return MySite.getSit(site);
	}

	public static void main(String[] args) {
		// 今日头条/热点数据url
		Spider.create(new MyPageProcessor()).addUrl(
				"https://m.toutiao.com/list/?tag=news_hot&ac=wap&count=20&format=json_raw&as=A1551E75BC4CF9C&cp=5E5C3CEFB9DCAE1&max_behot_time=1583123351&_signature=CXZIuwAAVy.txcPhjSEcTwl2SK&i=1583123351")
				.thread(1).run();
	}
}
