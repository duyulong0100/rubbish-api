package com.xserver.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfigConfigguration {
    private static final Logger logger = Logger.getLogger(ServletConfigConfigguration.class);

//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.addUrlMappings("/api/*", "/*");
//        return registration;
//    }
}
