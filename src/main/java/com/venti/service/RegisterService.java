package com.venti.service;

import com.venti.model.po.UserLogin;

public interface RegisterService {
    /**
     * 手机号注册
     * @param mobile
     * @return
     */
    UserLogin registerByMobile(String mobile);
}
