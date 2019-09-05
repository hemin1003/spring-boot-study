package com.md.demo.oauth.util;

public enum ResultCode {

	/** 成功 */
	SUCCESS("200", "成功"),

	/** 数据为空 */
	SUCCESS_NO_DATA("201", "数据为空"),

	/** 用户不存在 */
	SUCCESS_NO_USER("202", "用户不存在"),

	/** 今天的量已经跑光了 */
	SUCCESS_ALL_GONE("203", "今天的量已经跑光了"),

	/** 失败，请重试 */
	SUCCESS_FAIL("204", "失败，请重试");

	private ResultCode(String val, String msg) {
		this.val = val;
		this.msg = msg;
	}

	public String val() {
		return val;
	}

	public String msg() {
		return msg;
	}

	private String val;
	private String msg;

}
