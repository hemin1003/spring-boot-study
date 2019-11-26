package com.md.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(description = "通用参数")
public class JsonResult<T> implements Serializable {

	@ApiModelProperty(value = "响应业务状态", required = true, position = 1)
	private Integer status;

	@ApiModelProperty(value = "响应消息", required = true, position = 2)
	private String msg;

	@ApiModelProperty(value = "响应中的数据", required = true, position = 3)
	private T data;

	public static JsonResult build(Integer status, String msg, Object data) {
		return new JsonResult(status, msg, data);
	}

	public static JsonResult fromJSONObject(JSONObject jsonObject) {
		if (jsonObject != null) {
			if (jsonObject.containsKey("code")) {
				Integer status = jsonObject.getInteger("code");
				if (status == 0)
					status = CodeEnums.SUCCESS.getCode();
				if (status == 1)
					status = CodeEnums.ERROR.getCode();
				String msg = jsonObject.getString("msg");
				Object data = jsonObject.get("data");
				return new JsonResult(status, msg, data);
			} else {
				return new JsonResult(CodeEnums.ERROR.getCode(), "不正确的数据格式", null);
			}

		} else {
			return new JsonResult(CodeEnums.SERVICE_DOWN.getCode(), CodeEnums.SERVICE_DOWN.getMsg(), null);
		}

	}

	public static JsonResult ok(Object data) {
		return new JsonResult(data);
	}

	public static JsonResult ok() {
		return new JsonResult(null);
	}

	public static JsonResult build(Integer status, String msg) {
		return new JsonResult(status, msg, null);
	}

	public static JsonResult build(CodeEnums codeEnums) {
		return new JsonResult(codeEnums.getCode(), codeEnums.getMsg(), null);
	}

	public JsonResult(Integer status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public JsonResult(T data) {
		this.status = CodeEnums.SUCCESS.getCode();
		this.msg = "OK";
		this.data = data;
	}

	@JSONField(serialize = false)
	@JsonIgnore
	@ApiModelProperty(hidden = true)
	public boolean isSuccess() {
		return this.status.intValue() == CodeEnums.SUCCESS.getCode().intValue();
	}

	@JSONField(serialize = false)
	@JsonIgnore
	@ApiModelProperty(hidden = true)
	public T getDataSuc() {
		if (isSuccess()) {
			return this.data;
		}
		return null;
	}

	@Override
	public String toString() {
		return "JsonResult{" + "status=" + status + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}

}