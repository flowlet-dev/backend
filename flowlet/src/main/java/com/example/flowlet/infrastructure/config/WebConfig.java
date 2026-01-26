package com.example.flowlet.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * アプリケーション全体のWeb関連の設定を行うクラスです。
 * CORS（Cross-Origin Resource Sharing）の設定などを管理します。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.web.cors.allowed-origins:http://localhost:3000}")
    private String[] allowedOrigins;

    @Value("${spring.web.cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS}")
    private String[] allowedMethods;

    @Value("${spring.web.cors.allowed-headers:*}")
    private String[] allowedHeaders;

    /**
     * CORSの設定を登録します。
     * application.properties から取得した設定値に基づいて、許可されるオリジン、メソッド、ヘッダーを構成します。
     *
     * @param registry CORSレジストリ
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders)
                .allowCredentials(true);
    }
}
