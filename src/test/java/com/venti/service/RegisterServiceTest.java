package com.venti.service;

import com.venti.Application;
import com.venti.dao.repository.SysRoleRepository;
import com.venti.model.dto.UserRegisterDTO;
import com.venti.model.po.SysRole;
import com.venti.model.vo.ResultVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RegisterServiceTest {
    @Autowired
    RegisterService service;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Test
    public void registerByMobile() throws Exception {
    }

    @Test
    public void sendRegSM() throws Exception {
    }

    @Test
    public void registerByMail() throws Exception {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setMail("lieh_666@foxmail.com");
        dto.setPasswd("Xiaohe00.");
        ResultVO result = service.registerByMail(dto);
        Assert.assertEquals((long)result.getCode(), 200);
    }

    @Test
    public void findRole() throws Exception{

    }


}