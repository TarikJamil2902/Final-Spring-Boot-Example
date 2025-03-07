package com.tj.inventorySpringBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.css").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/*.js").addResourceLocations("/WEB-INF/js/");
        registry.addResourceHandler("/*.jpg").addResourceLocations("/WEB-INF/img/");
        registry.addResourceHandler("/*.png").addResourceLocations("/WEB-INF/img/");
        registry.addResourceHandler("/*.jpeg").addResourceLocations("/WEB-INF/img/");

    }
}
