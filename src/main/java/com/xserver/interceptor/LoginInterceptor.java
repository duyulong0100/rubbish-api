package com.xserver.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info(this.getClass().getName() + " preHandle.");
        // TODO 登录状态检测,添加逻辑
        HttpSession session = request.getSession();
        if (false) {
            response.setStatus(401);
            return false;
        }

        return true;
    }
}