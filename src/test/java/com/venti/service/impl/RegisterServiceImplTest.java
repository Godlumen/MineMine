package com.venti.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceImplTest {
    @Autowired
    private RegisterServiceImpl registerService;

    @Test
    @Transactional
    public void registerByMobile() {
        Assert.assertNotNull(registerService.registerByMobile("18049378259"));
    }
}