package com.md.demo.test.service;

import com.md.demo.test.entity.vo.Myuser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MMinbo
 * @since 2019-10-25
 */
public interface IMyuserService extends IService<Myuser> {
	public Myuser getUserByName(String name);
}
