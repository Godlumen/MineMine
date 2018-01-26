package com.venti.controller;

import com.venti.exception.MineMineException;
import com.venti.model.dto.UserLoginDTO;
import com.venti.model.vo.ResultVO;
import com.venti.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class LogController {
    //TODO
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVO login(@RequestBody UserLoginDTO dto) throws MineMineException {
        log.info("LogController--->login...");
        ResultVO resultVO = null;
        Subject currUser = SecurityUtils.getSubject();
        if (!currUser.isAuthenticated()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(dto.getMobile(), dto.getPasswd(),dto.getRememberMe());
                currUser.login(token);
                resultVO = ResultVOUtil.success();
            } catch (MineMineException e) {

            }
        }
        return resultVO;
    }

    //TODO
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultVO logout() {
        log.info("LogController--->logout...");
        ResultVO resultVO = null;
        try {
            //退出
            SecurityUtils.getSubject().logout();
            resultVO = ResultVOUtil.success();
        } catch (Exception e) {

        }
        return resultVO;
    }

}
