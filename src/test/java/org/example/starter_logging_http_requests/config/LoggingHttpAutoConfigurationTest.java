package org.example.starter_logging_http_requests.config;

import org.example.starter_logging_http_requests.bean.InterceptorBean;
import org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = "logging.http.enabled=true")
class LoggingHttpAutoConfigurationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Test loggingHttpInterceptor bean created")
    void testLoggingHttpInterceptorBeanCreated() {
        assertNotNull(applicationContext.getBean(LoggingHttpInterceptor.class));
    }

    @Test
    @DisplayName("Test interceptor bean created")
    void testInterceptorBeanCreated() {
        assertNotNull(applicationContext.getBean(InterceptorBean.class));
    }
}