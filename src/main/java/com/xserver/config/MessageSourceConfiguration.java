package com.xserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 */
@Configuration
public class MessageSourceConfiguration {
    @Value("${spring.messages.basename}")
    private String baseName;
    @Value("${spring.messages.encoding}")
    private String encoding;

    @Bean(name = "reloadMessageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename(baseName);
        messageBundle.setDefaultEncoding(encoding);
        return messageBundle;
    }
}
