package com.nkb.store.config;

import com.nkb.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/** 注册处理器拦截器 */
@Configuration
public class AutoInterceptorConfigurer implements WebMvcConfigurer {

    /** 拦截器配置 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 创建自定义拦截器对象
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        // 白名单
//        List<String> patterns = new ArrayList<>();
//        patterns.add("/bootstrap3/**");
//        patterns.add("/css/**");
//        patterns.add("/images/**");
//        patterns.add("/js/**");
//        patterns.add("/web/header.html");
//        patterns.add("/web/footer.html");
//        patterns.add("/web/register.html");
//        patterns.add("/web/login.html");
//        patterns.add("/web/index.html");
//        patterns.add("/web/product.html");
//        patterns.add("/user/reg");
//        patterns.add("/user/login");
//        patterns.add("/districts/**");
//        patterns.add("/products/**");
//        patterns.add("/districts/**");
//        patterns.add("/web/index.html");
//        patterns.add("/products/**");

        // 通过注册工具添加拦截器
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/web/login.html").excludePathPatterns("/**");
    }
}
