package com.venti.controller;

import com.venti.model.vo.ResultVO;
import com.venti.service.RegisterService;
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

    @RequestMapping(value = "/regMob",method = RequestMethod.GET)
    public ResultVO registerByMobile(@RequestParam("mobile") String mobile,@RequestParam("verifyCode") String verifyCode){
        log.info("RegisterController--->registerByMobile...");
        return registerService.registerByMobile(mobile,verifyCode);
    }

    @RequestMapping(value = "/regMail", method = RequestMethod.POST)
    public void registerByMail(@RequestBody String mail) {
        log.info("RegisterController--->registerByMail...");

    }
}