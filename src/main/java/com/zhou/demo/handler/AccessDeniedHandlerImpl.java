package com.zhou.demo.handler;

import com.alibaba.fastjson.JSON;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * 用户授权异常
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CommonResult<Object> result = new CommonResult(HttpStatus.FORBIDDEN.value(),"用户权限不足");
        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response,jsonString);
    }
}
