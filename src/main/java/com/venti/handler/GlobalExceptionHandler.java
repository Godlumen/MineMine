package com.venti.handler;

import com.venti.exception.MineMineException;
import com.venti.model.vo.ResultVO;
import com.venti.util.ResultVOUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MineMineException.class)
    public ResultVO MineMineExceptionHandler(MineMineException exception) throws RuntimeException {
        return ResultVOUtil.error(exception.getCode(), exception.getMessage());
    }

//    @ExceptionHandler(value = Exception.class)
//    public ResultVO ExceptionHandler(Exception exception) throws Exception {
//        return ResultVOUtil.error(500, "服务器异常，请联系管理员");
//    }
}
