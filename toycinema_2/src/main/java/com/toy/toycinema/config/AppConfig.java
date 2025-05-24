package com.toy.toycinema.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploadFiles/**")
                .addResourceLocations("file:////Users/j.h.jin/Library/Mobile%20Documents/com~apple~CloudDocs/WorkSpace/Spring_toy/uploadFiles/");
    }

}
