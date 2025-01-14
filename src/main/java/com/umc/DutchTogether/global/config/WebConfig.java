package com.umc.DutchTogether.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:5173", "https://umc.dutchtogether.com","http://www.dutchtogether.com/", "https://www.dutchtogether.com")
                .allowedMethods("OPTIONS","GET", "POST", "PUT", "PATCH", "DELETE");
    }
}