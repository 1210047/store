package com.nkb.store.interceptor;

import com.nkb.store.common.Base64Utils;
import com.nkb.store.entity.User;
import com.nkb.store.service.UserService;
import com.nkb.store.service.impl.UserServiceImpl;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 定义一个拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有重定向到登录
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+controller）
     * @return 如果true放行，false，拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {


        if (request.getSession().getAttribute("uid") == null) {
//            System.out.println(request.getSession().getAttribute("uid"));
            // 说明用户没有登录，则重定向到login.html页面
            response.sendRedirect("/web/login.html");
            // 结束后续调用
            return false;
        }
                return true;
    }
}

