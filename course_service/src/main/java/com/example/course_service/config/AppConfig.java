package com.example.course_service.config;

import com.example.course_service.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
