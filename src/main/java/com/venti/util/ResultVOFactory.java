package com.venti.util;

import com.venti.enums.ResultEnum;
import com.venti.model.vo.ResultVO;

public class ResultVOFactory {
    /**
     * 实例一个有数据的响应，并返回
     * @param status
     * @param o
     * @return
     */
    public static ResultVO create(ResultEnum status, Object o) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(status.getCode());
        resultVO.setMsg(status.getMsg());
        resultVO.setData(o);
        return resultVO;
    }

    /**
     * 实例一个无数据的响应并返回
     * @param status
     * @return
     */
    public static ResultVO create(ResultEnum status) {
        ResultVO resultVO = create(status, null);
        return resultVO;
    }
}
