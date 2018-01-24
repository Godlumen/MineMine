package com.venti.service;

import com.venti.model.po.UserLogin;
import com.venti.model.vo.ResultVO;

import java.util.Map;

public interface RegisterService {
    /**
     * 手机号注册
     **/
    ResultVO registerByMobile(String mobile,String verifyCode);

    /**
     * 发送验证短信，获取验证码
     */
    ResultVO sendRegSM (String mobile);
}
