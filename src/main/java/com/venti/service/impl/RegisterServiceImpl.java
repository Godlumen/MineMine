package com.venti.service.impl;

import com.venti.dao.repository.RegisterRepository;
import com.venti.model.po.UserLogin;
import com.venti.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public UserLogin registerByMobile(String mobile) {
        return null;
    }
}
