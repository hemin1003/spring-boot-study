package com.md.demo.dao;

import com.md.demo.vo.DemoEntity;

public interface DemoDao {

	void saveDemo(DemoEntity demoEntity);

	void removeDemo(DemoEntity demoEntity);

	void updateDemo(DemoEntity demoEntity);

	DemoEntity findDemoById(Long id);
}
