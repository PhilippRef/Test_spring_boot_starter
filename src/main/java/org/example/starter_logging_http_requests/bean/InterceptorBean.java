package org.example.starter_logging_http_requests.bean;

import org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс для настройки конфигурации MVC.
 */

public class InterceptorBean implements WebMvcConfigurer {

    /**
     * Метод добавляет созданный перехватчик запросов (LoggingHttpInterceptor) в конфигурацию Spring (MVC)
     * @param registry
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingHttpInterceptor());
    }
}
