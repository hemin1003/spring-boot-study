package com.md.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 使用MockMvc测试接口
 * 
 * @author Minbo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class) // 指定启动类
@AutoConfigureMockMvc // 测试接口用
@WebAppConfiguration
public class ApplicationMockTest {

	protected static Logger logger = LoggerFactory.getLogger(ApplicationMockTest.class);

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setupMockMvc() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Before
	public void testBefore() {
		logger.info("测试前，打印...");
	}

	@After
	public void testAfter() {
		logger.info("测试后，打印...");
	}

	@Test
	public void apiTest() throws Exception {
		String url = "/hello";
		String expectResult = "Hello，greetings from sprint-boot2-autotest";

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url))
				// 期望：200成功状态码
				.andExpect(MockMvcResultMatchers.status().isOk())
				// 期望：返回结果数据
				.andExpect(MockMvcResultMatchers.content().string(expectResult)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		logger.info("返回状态码status=" + status);

		String content = mvcResult.getResponse().getContentAsString();
		logger.info("返回结果数据content=" + content);
	}
}
