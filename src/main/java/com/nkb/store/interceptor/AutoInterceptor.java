package com.nkb.store.interceptor;

import com.nkb.store.common.Base64Utils;
import com.nkb.store.entity.User;
import com.nkb.store.service.UserService;
import com.nkb.store.service.impl.UserServiceImpl;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            String content = null;
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals("autoUser")){
                    content = cookie.getValue();
                }
        }

        if (content!=null){
            content = Base64Utils.decode(content);

            String[] split = content.split(":");

            String username = split[0];
            String password = split[1];

            UserService userService = new UserServiceImpl();
            User user = userService.login(username, password);
            System.out.println(user);
            if (user==null){
                response.sendRedirect("/web/login.html");
                return false;
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("loginUser",user);
                response.sendRedirect("/web/index.html");
                return true;
            }
        }


        }


        return false;
    }
}
