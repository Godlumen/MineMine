package com.venti.shiro;


import com.venti.dao.repository.RegLogRepository;
import com.venti.enums.ResultEnum;
import com.venti.enums.UserStatusEnum;
import com.venti.exception.MineMineException;
import com.venti.model.po.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.venti.enums.ResultEnum.*;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private RegLogRepository regLogRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数
    private final String SHIRO_LOG_COUNT = "shiro_log_count";
    //用户登录是否被锁定
    private final String SHIRO_IS_LOCKED = "shiro_is_locked";

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UserLogin user = (UserLogin) SecurityUtils.getSubject().getPrincipal();
        String id = user.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return null;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        UserLogin user = regLogRepository.findByMobileOrEmail(name, name);

        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        if ("yes".equals(opsForValue.get(SHIRO_IS_LOCKED + name))) {
            log.error("账号={}登录次数大于5，用户被锁定一分钟", name);
            throw new MineMineException(LOCKED_ACCOUNT);
        }

        if (user == null) {
            log.error("账号={}账号密码输入错误", name);
            throw new MineMineException(LOGIN_ERROR);
        } else {
            //登录计数一次
            opsForValue.increment(SHIRO_LOG_COUNT + name, 1);
            //登录次数大于5时，用户被锁定一分钟
            if (Integer.parseInt(opsForValue.get(SHIRO_LOG_COUNT + name)) >= 5) {
                opsForValue.set(SHIRO_IS_LOCKED + name, "yes");
                stringRedisTemplate.expire(SHIRO_IS_LOCKED + name, 1, TimeUnit.MINUTES);
            }
            if (!user.getPassword().equals(password)) {
                log.error("账号={}账号密码输入错误", name);
                throw new MineMineException(LOGIN_ERROR);
            } else {
                if (UserStatusEnum.DISABLED.getCode().equals(user.getStatus())) {
                    String id = user.getId();
                    log.error("账号={}用户被禁用，id={}", name, id);
                    throw new MineMineException(DISABLED_ACCOUNT);

                } else {
                    user.setLastLoginTime(new Date());
                    regLogRepository.save(user);
                    opsForValue.set(SHIRO_LOG_COUNT + name, "0");
                }
            }
        }
        String id = user.getId();
        log.info("账号={}登录成功，id={}", name, id);
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}