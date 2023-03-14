package com.carleonis.erpapi.security.teste;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig implements WebMvcConfigurer {
/*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH");
    }
   */ 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**");
    }

}