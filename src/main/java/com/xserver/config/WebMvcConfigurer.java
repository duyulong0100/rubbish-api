package com.xserver.config;

import com.xserver.common.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new Session.Resolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/docs").setViewName("redirect:/swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html/");
        registry.addResourceHandler("/others/**").addResourceLocations("classpath:/static/others/");
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/static/web/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
