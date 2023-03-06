package com.zhou.demo.filter;

import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录校验过滤器
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     *
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.获取token
        String token =request.getHeader("Authorization");
        System.out.println("token===="+StringUtils.hasText(token));
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //2.解析token
        String userId=null;
        if(JwtUtils.checkToken(token)){
            filterChain.doFilter(request,response);
        }
        userId = JwtUtils.getUserId(token);
        System.out.println("---"+userId);
        //3.从redis中获取用户信息
        String redisKey="login:"+userId;
        LoginUser loginuser = (LoginUser)redisTemplate.opsForValue().get(redisKey);
        //4.存入SecurityContextHolder
        if(ObjectUtils.isEmpty(loginuser)){
            throw new RuntimeException("用户未登录");
        }
        System.out.println(loginuser.toString());
        System.out.println(loginuser.getAuthorities().toString());
        //TODO 获取权限信息封装到usernampassword的Authenticated中。
        UsernamePasswordAuthenticationToken AuthenticatedToken =
                new UsernamePasswordAuthenticationToken(loginuser, null, loginuser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(AuthenticatedToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
