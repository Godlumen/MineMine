package com.venti.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "成功"),
    MOBILE_EXITS(1001, "手机号已存在"),
    SMS_ERROR(1002, "短信发送失败"),
    REDIS_NOT_EXISTS(1003, "Redis中不存在该参数"),
    VERIFY_FAIL(1004, "手机号验证失败"),
    MAIL_EXITS(1005, "邮箱已存在"),
    SEND_MAIL_ERROR(1006, "邮件发送失败"),
    LOCKED_ACCOUNT(600,"密码输入错误次数大于5次，帐号已经禁止登录"),
    LOGIN_ERROR(601,"登录失败，密码输入错误"),
    DISABLED_ACCOUNT(602,"此帐号已经设置为禁止登录");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
