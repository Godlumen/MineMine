package com.venti.model.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    /** 状态码 **/
    private Integer code;
    /** 提示信息 **/
    private String msg;
    /** 主体 **/
    private T data;
}
