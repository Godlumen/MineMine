package com.venti.util;

import com.venti.enums.ResultEnum;
import com.venti.model.vo.ResultVO;

public class ResultVOUtil {
    /**
     * 响应成功
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
     * @param resultEnum
     * @return
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }
}
