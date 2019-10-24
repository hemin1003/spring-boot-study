package com.md.demo.test.dao;

import com.md.demo.test.entity.vo.Myuser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MMinbo
 * @since 2019-10-25
 */
public interface MyuserMapper extends BaseMapper<Myuser> {
	// 方法名自动对应
	public Myuser getUserByName(String name);
}
