package com.venti.shiro.filter;

import com.venti.enums.ResultEnum;
import com.venti.exception.MineMineException;
import com.venti.model.po.UserLogin;
import com.venti.util.JsonUtil;
import com.venti.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.logging.Handler;

@Slf4j
public class ShiroLoginFilter extends AdviceFilter {

    /**
     * 访问任何请求前，判断是否登录，返回json
     *
     * @param servletRequest
     * @param servletResponse
     * @return false-没有登陆拦截，不会继续执行shiro下层的拦截器 true-继续向下过滤
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.info("请求拦截开始...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");//支持跨域访问
        httpServletResponse.setCharacterEncoding("utf-8");
        UserLogin User = (UserLogin) SecurityUtils.getSubject().getPrincipal();
        System.out.println(User);
        String URI = httpServletRequest.getRequestURI();
        log.info("请求的链接地址为={}", URI);
        if (User == null && !(URI.contains("swagger") || URI.contains("api-docs") || URI.contains("login")
                || URI.contains("configuration") || URI.contains("reg"))) {
            String body = JsonUtil.toJson(ResultVOUtil.error(ResultEnum.NOT_LOGIN.getCode(), ResultEnum.NOT_LOGIN.getMsg()));
            PrintWriter writer = null;
            try {
                writer = httpServletResponse.getWriter();
                writer.write(body);
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null)
                    writer.close();
            }

        }
        return true;
    }
}
