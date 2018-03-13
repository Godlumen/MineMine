package com.venti.shiro.filter;

import com.venti.enums.ResultEnum;
import com.venti.util.JsonUtil;
import com.venti.util.ResultVOUtil;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroPermsFilter extends PermissionsAuthorizationFilter {

    /**
     * 权限鉴权失败处理
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
        String body = JsonUtil.toJson(ResultVOUtil.error(ResultEnum.NOT_AUTHZ_PERMS.getCode(), ResultEnum.NOT_AUTHZ_PERMS.getMsg()));
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
