package com.xserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 604800)
@Configuration
public class HttpSessionConfig {
    CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();
    HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {

        return new HttpSessionStrategy() {
            @Override
            public String getRequestedSessionId(HttpServletRequest request) {
                String id = headerHttpSessionStrategy.getRequestedSessionId(request);
                if (id == null) {
                    id = cookieHttpSessionStrategy.getRequestedSessionId(request);
                }
                return id;
            }

            @Override
            public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {

                cookieHttpSessionStrategy.onNewSession(session, request, response);
                headerHttpSessionStrategy.onNewSession(session, request, response);
            }

            @Override
            public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
                cookieHttpSessionStrategy.onInvalidateSession(request, response);
                headerHttpSessionStrategy.onInvalidateSession(request, response);
            }
        };
    }

}
