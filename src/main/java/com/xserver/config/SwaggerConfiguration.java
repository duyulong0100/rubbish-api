package com.xserver.config;

import com.xserver.common.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${swagger.host}")
    private String host;

    @Value("${swagger.title}")
    private String title;

    @Bean
    public Docket buildDocket() {
        String basePackage = "com.xserver";

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo()).host(host)
                .useDefaultResponseMessages(false).select().apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()).build().ignoredParameterTypes(Session.class);
    }

    public ApiInfo buildApiInfo() {
        return new ApiInfoBuilder().title(title).version("1.0").build();
    }

}
