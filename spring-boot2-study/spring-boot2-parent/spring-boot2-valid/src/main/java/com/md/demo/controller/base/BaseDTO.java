package com.md.demo.controller.base;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("请求参数基础模型")
public class BaseDTO<T> {

	@ApiModelProperty(value = "轨迹跟踪traceId/长度固定19位，由服务端下发", example = "2002271818562848295", required = true)
	@NotEmpty(message = "轨迹跟踪traceId不能为空")
	private String traceId;

	@ApiModelProperty(value = "请求requestId/长度固定8位(字母转大写)", example = "ABCD1234", required = true)
	@NotEmpty(message = "请求requestId不能为空")
	private String requestId;

	@ApiModelProperty(value = "请求时间/格式要求：时间戳/毫秒", example = "1581928973000", required = true)
	@NotEmpty(message = "请求时间不能为空")
	private String time;

	@ApiModelProperty(value = "请求源标识，填appId值", example = "A123456", required = true)
	@NotEmpty(message = "请求源标识不能为空")
	private String caller;

	@ApiModelProperty(value = "请求接口名", example = "getUserById", required = true)
	@NotEmpty(message = "请求接口名不能为空")
	private String api;

	// 嵌套必须加 @Valid，否则嵌套中的验证不生效
	@Valid
	@ApiModelProperty(value = "业务参数", required = true)
	private T param;

	@ApiModelProperty(value = "接口签名值：把业务参数param按照ASCII码排序，再拼接秘钥后进行md5加密，得出32位值(字母转大写)", required = true)
	private String sign;

}
