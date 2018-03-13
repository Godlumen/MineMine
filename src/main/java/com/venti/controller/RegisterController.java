package com.venti.controller;

import com.venti.model.dto.UserRegisterDTO;
import com.venti.model.vo.ResultVO;
import com.venti.service.RegisterService;
import com.venti.shiro.filter.NotAuthc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/reg")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/verifyCode",method = RequestMethod.GET)
    public ResultVO<Map<String,String>> getVerifyCode(@RequestParam("mobile") String mobile){
        log.info("RegisterController--->getVerifyCode...");
        return registerService.sendRegSM(mobile);
    }

    @RequestMapping(value = "/regMob",method = RequestMethod.POST)
    public ResultVO registerByMobile(@RequestBody UserRegisterDTO dto){
        log.info("RegisterController--->registerByMobile...");
        return registerService.registerByMobile(dto);
    }

    @RequestMapping(value = "/regMail", method = RequestMethod.POST)
    public ResultVO registerByMail(@RequestBody UserRegisterDTO dto) {
        log.info("RegisterController--->registerByMail...");
        return registerService.registerByMail(dto);
    }
}
