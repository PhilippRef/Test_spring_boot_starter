package org.example.starter_logging_http_requests.config;

import org.example.starter_logging_http_requests.bean.InterceptorBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(LoggingHttpProperties.class)
@ConditionalOnProperty(name = "logging.http", value = "enabled", havingValue = "true")
public class LoggingHttpAutoConfiguration {

    @Bean
    @ConditionalOnExpression("${logging.http.enabled:false}")
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
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

//    @Bean
//    @ConditionalOnMissingBean
//    public LogStructure logStructure() {
//        return new LogStructure();
//    }


