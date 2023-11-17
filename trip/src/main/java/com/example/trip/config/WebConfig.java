package com.example.trip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final String[] ALLOWED_ORIGIN = {
            "http://localhost:5174",
            "http://70.12.50.61:5174"
    };
    private final String[] ALLOWED_METHOD = {
            "GET", "POST","DELETE", "PUT"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALLOWED_ORIGIN)
//                .allowedMethods(ALLOWED_METHOD)
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3000);
    }
}
