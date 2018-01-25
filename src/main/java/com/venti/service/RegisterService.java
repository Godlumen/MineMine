package com.venti.service;

import com.venti.model.dto.UserRegisterDTO;
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


    /**
     * 使用邮箱注册，
     * 1.保存用户信息，状态为未激活
     * 2.发送激活邮件到用户邮箱
     * @param dto
     * @return
     */
    ResultVO registerByMail(UserRegisterDTO dto);
}
