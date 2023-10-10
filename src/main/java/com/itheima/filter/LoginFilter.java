package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义一个过滤器，设置拦截路径为所有。
 * 实现里面的一个方法
 */

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("放行前逻辑...");
        //1.判断其是不是登录请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        //判断url是否包含login，如果包含，则放行。
        if(url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //2.判断是否含有jwt令牌，如果没有或者解析错误，则会拦截，反之放行。
        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头中不包含令牌，拦截该请求...");
            Result s = Result.error("NOT_LOGIN");
            String e = JSONObject.toJSONString(s);

            response.getWriter().write(e);
            return;
        }

        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            //打印错误信息
            e.printStackTrace();
            log.info("解析令牌错误，拦截该请求");
            Result r = Result.error("NOT_LOGIN");
            //将实体类转化为json，然后返回到前端。
            String error = JSONObject.toJSONString(r);

            response.getWriter().write(error);
            return;
        }

        //放行

        filterChain.doFilter(servletRequest,servletResponse);


    }
}
