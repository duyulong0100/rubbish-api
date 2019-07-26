package com.xserver.tp.http;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpClientConfig {

    @Bean("okHttpClient")
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();

    }
    // TODO可以补充HTTPS请求
}