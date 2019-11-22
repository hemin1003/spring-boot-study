package com.md.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class UserVO {

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "用户ID")
	private String userId;

	@ApiModelProperty(value = "博客地址")
	private String blogUrl;

	@ApiModelProperty(value = "微信号")
	private String wechat;
}
