package com.md.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.demo.service.DemoService;
import com.md.demo.util.JsonResult;
import com.md.demo.util.ResultCode;
import com.md.demo.vo.DemoEntity;

/**
 * @author Minbo
 */
@RestController
public class InitRest {

	protected static Logger logger = LoggerFactory.getLogger(InitRest.class);

	/**
	 * http://localhost:9090/hello
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public String hello() {
		logger.info("hello");
		return "Hello greetings from spring-boot2-mongodb";
	}

	@Autowired
	private DemoService demoService;

	/**
	 * 新增
	 */
	@GetMapping("/saveDemoTest")
	public JsonResult saveDemoTest() {

		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setId(1L);
		demoEntity.setTitle("使用mongodb");
		demoEntity.setDescription("这是描述");
		demoEntity.setBy("minbo");
		demoEntity.setUrl("https://blog.csdn.net/hemin1003");

		this.demoService.addDemo(demoEntity);

		demoEntity = new DemoEntity();
		demoEntity.setId(2L);
		demoEntity.setTitle("使用mongodb2");
		demoEntity.setDescription("这是描述2");
		demoEntity.setBy("minbo2");
		demoEntity.setUrl("https://blog.csdn.net/hemin1003");

		this.demoService.addDemo(demoEntity);

		return new JsonResult(ResultCode.SUCCESS);
	}

	/**
	 * 删除
	 */
	@GetMapping("/removeDemoTest")
	public JsonResult removeDemoTest(Long id) {
		this.demoService.removeDemo(id);

		return new JsonResult(ResultCode.SUCCESS);
	}

	/**
	 * 修改
	 */
	@GetMapping("/updateDemoTest")
	public JsonResult updateDemoTest() {

		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setId(1L);
		demoEntity.setTitle("使用mongodb3");
		demoEntity.setDescription("这是描述3");
		demoEntity.setBy("minbo3");
		demoEntity.setUrl("https://blog.csdn.net/hemin1003");

		this.demoService.modifyDemo(demoEntity);

		return new JsonResult(ResultCode.SUCCESS, demoEntity);
	}

	/**
	 * 查找
	 */
	@GetMapping("/findDemoByIdTest")
	public JsonResult findDemoByIdTest(Long id) {
		DemoEntity demoEntity = this.demoService.findDemoById(id);
		System.out.println(demoEntity.toString());
		return new JsonResult(ResultCode.SUCCESS, demoEntity);
	}
}