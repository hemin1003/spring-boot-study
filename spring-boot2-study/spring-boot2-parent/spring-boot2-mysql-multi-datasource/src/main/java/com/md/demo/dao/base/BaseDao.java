package com.md.demo.dao.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {
	
	/**
	 * 默认库-主库
	 */
	@Resource(name = "masterSessionTemplate")
	@Autowired
	public SqlSessionTemplate sqlSessionTemplateOfMaster;
	
	/**
	 * 灾备库-用作读库
	 */
	@Resource(name = "slaveSessionTemplate")
	@Autowired
	public SqlSessionTemplate sqlSessionTemplateOfSlave;
}
