package com.venti.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    /** 状态码 **/
    private Integer code;
    /** 提示信息 **/
    private String msg;
    /** 主体 **/
    private T data;
}
