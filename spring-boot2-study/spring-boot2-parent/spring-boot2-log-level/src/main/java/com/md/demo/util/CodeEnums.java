package com.md.demo.util;

public enum CodeEnums {


    SUCCESS(Integer.valueOf(200), "操作成功"),
    ERROR(Integer.valueOf(-1), "操作失败"),
    CONTAINS_ERROR(Integer.valueOf(201),"操作完成，但包含错误"),

    //--------------系统参数----------------
    SYSTEM_ERR(Integer.valueOf(100000), "系统错误"),
    PARA_ERR(Integer.valueOf(100001), "请求参数错误"),
    STATUS_FORBIDDEN(Integer.valueOf(100002), "禁止访问"),
    SERVICE_DOWN(Integer.valueOf(100003), "微服务不可用"),
    SERVICE_BUSY(Integer.valueOf(1000031), "服务器繁忙，请稍后再试"),
    SIGN_ERR(Integer.valueOf(100004), "url sign 签名不一致"),
    TIMESTAMP_ERR(Integer.valueOf(100005), "timestamp与服务器时间相差较大"),
    NONCE_REPEAT(Integer.valueOf(100006), "重复提交"),
    INVALID_TOKEN(Integer.valueOf(100007), "无效的token"),
    //EXPIRED_TOKEN(Integer.valueOf(100008), "token已经过期"),
    JSONMAPPINGEXCEPTION(Integer.valueOf(100009), "JSON映射异常"),
    SYS_PARAMETER_NULL(Integer.valueOf(100010), "请联系管理员配置正确相关参数"),
    SYS_BIZ_ERR(Integer.valueOf(100011), "业务逻辑异常"),
    PARAM_VALIDATE_REFUSE(Integer.valueOf(100012), "校验不通过"),
    REPEAT(Integer.valueOf(100013), "网络开小差，请稍后重试"),
    //---------end--系统参数----------------


    //--------------登录----------------
    USER_NOT_LOGIN(Integer.valueOf(110000), "用户未登录"),
    USER_EXIST(Integer.valueOf(110001), "用户已经存在"),
    USER_NOT_EXIST(Integer.valueOf(110002), "用户不存在"),
    USER_IS_LOCK(Integer.valueOf(110003), "用户被冻结"),
    USER_PWD_FAIL(Integer.valueOf(110004), "用户名或密码错误"),
    //---------end--登录----------------

    INFO_NOT_ALL(Integer.valueOf(110005), "信息不全"),


    UP_HAS_USER(Integer.valueOf(200000), "当前手机号码已注册！！！"),
    UP_NOT_USER(Integer.valueOf(200002), "当前手机号码未注册"),
    UP_ERR(Integer.valueOf(200001), "接口异常"),
    UP_INVITEPOSTER_NULL(Integer.valueOf(200004), "生产推广海报出现异常，请联系推荐人！"),

    //--------------公共业务参数----------------

    HSRJ_TOKEN_NOT_VALID(Integer.valueOf(401), "TOKEN无效"),
    REDO(Integer.valueOf(10025), "重复操作"),
    EXIST_NAME(Integer.valueOf(10002), "名称已经存在"),
    MULTIPLE_RECORD(Integer.valueOf(10053), "存在多条记录"),
    RECORD_NOT_EXIST(Integer.valueOf(10013), "暂无数据"),
    RECORD_EXIST(Integer.valueOf(10014), "记录已经存在"),
    USER_ROLE_LIMIT(Integer.valueOf(10008), "用户没有操作的权限"),
    EMAIL_USED(Integer.valueOf(10009), "邮箱已经被使用"),
    USER_VERSION_NULL(Integer.valueOf(1330004), "请联系管理员配置不存在的版本信息"),


    //--------------业务参数----------------
    USER_SPREAD_NOT_EXIST(Integer.valueOf(10021), "邀请码无效"),
    VALIDATE_CODE_NOT(Integer.valueOf(10104), "验证码不正确"),
    SMS_TIME_OUT(Integer.valueOf(10030), "短信验证超时或不存在"),


    STORE_APROVING(Integer.valueOf(11101), "店铺审核中，请稍等"),
    STORE_NOT(Integer.valueOf(11102), "暂无店铺"),


    USER_CANT_CHANGE_PASSWORD(Integer.valueOf(10012), "原始密码不正确"),

    NONE_OPERATE_RECORDS(Integer.valueOf(10015), "没有需要操作的记录"),
    INVALID_USER(Integer.valueOf(10016), "无效的用户"),
    USER_ACCOUNT_EMPTY(Integer.valueOf(10020), "用户账户信息为空"),
    USER_RECIPIENT_EMPTY(Integer.valueOf(10022), "用户收款信息为空"),
    USER_BALANCE_NOT_ENOUGH(Integer.valueOf(10023), "用户余额不足"),


    USER_DEFAULT_STORE_NOT_EXIST(Integer.valueOf(10024), "用户店铺不存在"),
    NOT_VALID_AUDIT_STATUS(Integer.valueOf(10026), "无效的审核状态"),
    AUDIT_STATUS_CHANGED(Integer.valueOf(10028), "审核失败,状态已改变"),

    ACTIVITY_UNAVAILABLE(Integer.valueOf(10088), "活动过期或名额已经抢光"),

    MORE_THEN_QULITY_UPPER_LIMIT(Integer.valueOf(100100), "超出限购数量"),
    //---------end--业务参数----------------

    //--------------第三方参数----------------
    ERR_FRAMEWORK(Integer.valueOf(30001), "系统异常"),
    OK_FRAMEWORK(Integer.valueOf(0), "操作成功"),
    SERVICE_DOWN_FRAMEWORK(Integer.valueOf(30000), "微服务不可用"),
    //---------end--第三方参数----------------
    ;
    private Integer code;
    private String msg;

    private CodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
