package com.tonde.maisonchapback.config;

import com.tonde.maisonchapback.exceptions.CustomLogger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CustomLogger.log("INFO", "WebMvcConfig");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
