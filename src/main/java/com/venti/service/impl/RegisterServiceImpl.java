package com.venti.service.impl;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.venti.constant.SMSConstant;
import com.venti.dao.repository.RegisterRepository;
import com.venti.enums.ResultEnum;
import com.venti.exception.MineMineException;
import com.venti.model.dto.UserRegisterDTO;
import com.venti.model.po.UserLogin;
import com.venti.model.vo.ResultVO;
import com.venti.service.IMailService;
import com.venti.service.RegisterService;
import com.venti.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IMailService mailService;

    private static final Long MAIL_VERIFY_WAIT_TIME = 600L;

    /**
     * 手机注册（验证码验证）
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public ResultVO registerByMobile(UserRegisterDTO dto) {

        String mobile = dto.getMobile();
        String verifyCode = dto.getVerifyCode();
        String userName = dto.getUserName();
        String passwd = dto.getPasswd();
        String value = stringRedisTemplate.opsForValue().get(mobile);
        //判断Redis中Key是否存在请求的手机号
        if (value != null) {
            UserLogin userLogin = registerRepository.findByMobile(mobile);
            //判断填写验证码是否一致
            if (value == verifyCode) {
                log.info("手机号={},验证成功！", mobile);
                //修改数据库将用户状态变为已激活
                userLogin.setStatus(1);
                userLogin.setUserName(userName);
                userLogin.setPassword(passwd);
                registerRepository.save(userLogin);
                return ResultVOUtil.success();
            } else {
                log.error("手机号={},验证失败！", mobile);
                throw new MineMineException(ResultEnum.VERIFY_FAIL);
            }
        } else {
            log.error("手机号={},不存在！", mobile);
            throw new MineMineException(ResultEnum.REDIS_NOT_EXISTS);
        }
    }

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @return
     */
    @Override
    @Transactional
    public ResultVO<Map<String, String>> sendRegSM(String mobile) {

        UserLogin userLogin = registerRepository.findByMobile(mobile);
        //验证手机号是否存在
        if (userLogin != null) {
            log.error("手机号={},已存在！", mobile);
            throw new MineMineException(ResultEnum.MOBILE_EXITS);
        }
        //若不存在，发送验证短信
        else {
            String id = KeyUtil.getUniqueKey();
            ArrayList<String> params = null;
            String verifyCode = RandomUtil.getRandomNum(6);
            params.add(verifyCode);
            log.info("向手机号={}发送短信验证码...", mobile);
            SmsSingleSenderResult resultBody = SMSUtil.send2One(SMSConstant.REG_TEMPLATE_ID, params, mobile);
            //短信验证码发送成功，存入数据库（未激活），验证码存入Redis
            if (resultBody.result == 0) {
                log.error("向手机号={}发送短信验证码成功！", mobile);
                //将记录存入数据库
                userLogin.setId(id);
                userLogin.setMobile(mobile);
                registerRepository.save(userLogin);
                //将验证码存入Redis(60秒过期)
                stringRedisTemplate.opsForValue().set(mobile, verifyCode, 60, TimeUnit.SECONDS);
                //返回Json
                Map<String, String> map = new HashMap<String, String>();
                map.put("verifyCode", verifyCode);
                return ResultVOUtil.success(map);
            }
            //短信验证码发送失败
            else {
                log.error("向手机号={}发送短信验证码失败！", mobile);
                throw new MineMineException(ResultEnum.SMS_ERROR);
            }
        }
    }

    @Override
    @Transactional
    public ResultVO registerByMail(UserRegisterDTO dto) {

        UserLogin userLogin = registerRepository.findByEmail(dto.getMail());
        if (userLogin != null) {//唯一性验证
            log.error("邮箱号={},已存在！", dto.getMail());
            throw new MineMineException(ResultEnum.MAIL_EXITS);
        }
        String id = KeyUtil.getUniqueKey();
        //获取token,并发送激活邮件
        String token = TokenUtil.token(id);
        try {
            mailService.sendverifyMail(dto.getMail(), token);
        } catch (Exception e) {
            log.error("向手机号={}发送短信验证码失败！", dto.getMail());
            throw new MineMineException(ResultEnum.SEND_MAIL_ERROR);
        }
        //信息保存到数据库中
        userLogin.setId(id);
        userLogin.setEmail(dto.getMail());
        userLogin.setUserName(dto.getUserName());
        userLogin.setPassword(dto.getPasswd());
        registerRepository.save(userLogin);
        //token存入redis,保存10min
        stringRedisTemplate.opsForValue().set(dto.getMail(), token, MAIL_VERIFY_WAIT_TIME, TimeUnit.MINUTES);

        return ResultVOUtil.success();
    }
}
