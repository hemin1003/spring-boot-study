package com.md.demo.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("根据用户ID标识查询")
public class GetByIdDTO {

	@ApiModelProperty(value = "用户ID标识", required = true)
	@NotEmpty(message = "用户ID标识不能为空")
	private String id;

	// 当配置多个验证字段，验证失败时提示内容会一起返回
	
//	@ApiModelProperty(value = "用户名", required = true)
//	@NotEmpty(message = "用户名不能为空")
//	private String name;
	
}
