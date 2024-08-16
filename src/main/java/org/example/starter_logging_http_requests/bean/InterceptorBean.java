package org.example.starter_logging_http_requests.bean;

import org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class InterceptorBean implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingHttpInterceptor());
    }
}
