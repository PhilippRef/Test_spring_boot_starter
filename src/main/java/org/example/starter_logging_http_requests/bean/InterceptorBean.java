package org.example.starter_logging_http_requests.bean;

import lombok.extern.slf4j.Slf4j;
import org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
public class InterceptorBean implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingHttpInterceptor());
    }
}
