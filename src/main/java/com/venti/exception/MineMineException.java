package com.venti.exception;

import com.venti.enums.ResultEnum;
import lombok.Getter;

@Getter
public class MineMineException extends RuntimeException {

    private Integer code;
    private String msg;

    public MineMineException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public MineMineException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
