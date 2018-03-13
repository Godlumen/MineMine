package com.venti.shiro.filter;

import com.venti.enums.ResultEnum;
import com.venti.util.JsonUtil;
import com.venti.util.ResultVOUtil;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroRolesFilter extends RolesAuthorizationFilter {

    /**
     * 角色鉴权失败处理
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        String body = JsonUtil.toJson(ResultVOUtil.error(ResultEnum.NOT_AUTHZ_ROLES.getCode(), ResultEnum.NOT_AUTHZ_ROLES.getMsg()));
        try {
            writer = response.getWriter();
            writer.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
        return false;
    }
}
