package com.zhou.demo.handler;

import com.alibaba.fastjson.JSON;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    /**
     * 用户认证异常
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        CommonResult<Object> result = new CommonResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败 请查询登录");
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response,jsonString);
    }
}
