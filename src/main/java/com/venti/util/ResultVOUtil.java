package com.venti.util;

import com.venti.enums.ResultEnum;
import com.venti.model.vo.ResultVO;

public class ResultVOUtil {
    /**
     * 响应成功
     *
     * @param o
     * @return
     */
    public static ResultVO success(Object o) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    /**
     * 响应失败
     *
     * @param code,msg
     * @return
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
