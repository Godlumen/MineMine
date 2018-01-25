package com.venti.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "成功"),
    MOBILE_EXITS(1001, "手机号已存在"),
    SMS_ERROR(1002, "短信发送失败"),
    REDIS_NOT_EXISTS(1003, "Redis中不存在该参数"),
    VERIFY_FAIL(1004, "手机号验证失败");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
