package com.md.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.md.demo.dao.DemoDao;
import com.md.demo.service.DemoService;
import com.md.demo.vo.DemoEntity;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;

	@Override
	public void addDemo(DemoEntity demoEntity) {
		this.demoDao.saveDemo(demoEntity);
	}

	@Override
	public void removeDemo(Long id) {
		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setId(id);
		this.demoDao.removeDemo(demoEntity);
	}

	@Override
	public void modifyDemo(DemoEntity demoEntity) {
		this.demoDao.updateDemo(demoEntity);
	}

	@Override
	public DemoEntity findDemoById(Long id) {
		return this.demoDao.findDemoById(id);
	}

}