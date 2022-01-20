package com.example.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
        public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("MainPage");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/MainPage_Athlete").setViewName("MainPage_Athlete");
                registry.addViewController("/MainPage_Admin").setViewName("MainPage_Admin");
                registry.addViewController("/MainPage").setViewName("MainPage");
        }
}