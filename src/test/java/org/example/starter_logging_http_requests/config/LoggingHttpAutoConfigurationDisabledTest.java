package org.example.starter_logging_http_requests.config;

import org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = "logging.http.enabled=false")
class LoggingHttpAutoConfigurationDisabledTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Test loggingHttpInterceptor bean not created")
    void testLoggingHttpInterceptorBeanNotCreated() {
        assertFalse(applicationContext.containsBean("loggingHttpInterceptor"));

    }

    @Test
    @DisplayName("Test interceptor bean not created")
    void testInterceptorBeanNotCreated() {
        assertFalse(applicationContext.containsBean("interceptorBean"));
    }
}
