package com.venti.handler;

import com.venti.exception.MineMineException;
import com.venti.model.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MineMineException.class)
    public ResultVO MineMineExceptionHandler(MineMineException exception) throws RuntimeException{
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(exception.getCode());
        resultVO.setMsg(exception.getMessage());
        return resultVO;
    }


}
