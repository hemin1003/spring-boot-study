package com.md.demo.test.service.impl;

import com.md.demo.test.entity.vo.Myuser;
import com.md.demo.test.dao.MyuserMapper;
import com.md.demo.test.service.IMyuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MMinbo
 * @since 2019-10-25
 */
@Service
public class MyuserServiceImpl extends ServiceImpl<MyuserMapper, Myuser> implements IMyuserService {

	@Override
	public Myuser getUserByName(String name) {
		return this.baseMapper.getUserByName(name);
	}

}
