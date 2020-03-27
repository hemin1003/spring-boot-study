package com.md.demo.service;

import com.md.demo.entity.vo.Users;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Minbo
 * @since 2020-03-27
 */
public interface IUsersService extends IService<Users> {

	public List<Users> listDbInfo();
}
