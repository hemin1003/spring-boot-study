package com.md.demo.pay.utils.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 提现记录
 * </p>
 *
 * @author minbo
 */
@Data
@ApiModel(value = "PayWithdrawHis对象", description = "提现记录")
public class PayWithdrawHis implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "订单流水号")
	private String orderId;

	@ApiModelProperty(value = "打款账户号")
	private String Account;
	
	@ApiModelProperty(value = "账户姓名")
	private String name;

	@ApiModelProperty(value = "系统用户ID")
	private String sysUserId;

	@ApiModelProperty(value = "AppID")
	private String appId;

	@ApiModelProperty(value = "兑换金币值")
	private String gold;

	@ApiModelProperty(value = "提现金额")
	private String income;

	@ApiModelProperty(value = "提现申请时间")
	private Date applyTime;

	@ApiModelProperty(value = "订单状态类型值，1/2/3/4")
	private Integer orderStatus;

	@ApiModelProperty(value = "订单状态类型名1处理中，2提现成功，3审核中，4提现失败")
	private String orderStatusName;

	@ApiModelProperty(value = "提现完成时间")
	private Date finishedTime;

	@ApiModelProperty(value = "提现失败原因")
	private String failErrorMsg;

}
