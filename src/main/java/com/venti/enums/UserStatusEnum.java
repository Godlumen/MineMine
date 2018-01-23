package com.venti.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

    INACTIVE(0, "用户账号未激活验证"),
    ACTIVE(1, "用户账号成功激活");

    private Integer code;
    private String msg;

    UserStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
