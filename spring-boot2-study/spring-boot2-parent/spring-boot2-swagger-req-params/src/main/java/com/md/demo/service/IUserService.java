package com.md.demo.service;

import com.md.demo.dto.GetByIdDTO;
import com.md.demo.vo.UserVO;

/**
 * 用户信息中心
 * 
 * @author Minbo
 *
 */
public interface IUserService {

	/**
	 * 根据id获取用户信息
	 * 
	 * @param dto
	 * @return
	 */
	public UserVO getUserById(GetByIdDTO dto);
}
