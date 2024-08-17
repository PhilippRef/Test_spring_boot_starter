package org.example.starter_logging_http_requests.config;

import org.example.starter_logging_http_requests.bean.InterceptorBean;
import org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Класс для конфигурации starter
 */

@AutoConfiguration
/**
 * Добавляем свойства из класса LoggingHttpProperties
 */
@EnableConfigurationProperties(LoggingHttpProperties.class)
@ConditionalOnProperty(prefix = "logging.http", value = "enabled", havingValue = "true")
public class LoggingHttpAutoConfiguration {

    /**
     * Регистрируем bean LoggingHttpInterceptor, если в application.property задано значение true
     * @return LoggingHttpInterceptor
     */

    @Bean
    @ConditionalOnExpression("${logging.http.enabled:false}")
    public LoggingHttpInterceptor loggingHttpInterceptor() {
        return new LoggingHttpInterceptor();
    }

    /**
     * Регистрируем bean InterceptorBean в случае его отсутствия
     * @return InterceptorBean
     */
    @Bean
    @ConditionalOnMissingBean
    public InterceptorBean interceptorBean() {
        return new InterceptorBean();
    }

}
