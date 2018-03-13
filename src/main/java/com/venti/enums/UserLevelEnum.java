package com.venti.enums;

import lombok.Getter;

@Getter
public enum UserLevelEnum {

    ADMIN(1, "管理员"),
    VIP(2, "会员"),
    GENERAL_USER(3, "普通用户");

    private Integer code;
    private String msg;

    UserLevelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
