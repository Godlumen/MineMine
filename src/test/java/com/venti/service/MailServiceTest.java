package com.venti.service;

import com.venti.Application;
import com.venti.service.impl.MailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class MailServiceTest {
    @Autowired
    private MailServiceImpl mailService;

    private String to = "lieh_666@foxmail.com";
    @Test
    public void sendSimpleMail() throws Exception {
        mailService.sendSimpleMail(to, "主题：简单邮件", "测试邮件内容");
    }

}