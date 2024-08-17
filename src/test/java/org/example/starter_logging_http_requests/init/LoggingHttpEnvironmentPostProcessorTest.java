package org.example.starter_logging_http_requests.init;

import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoggingHttpEnvironmentPostProcessorTest {

    private final ConfigurableEnvironment environment =
            Mockito.mock(ConfigurableEnvironment.class);

    private final SpringApplication application =
            Mockito.mock(SpringApplication.class);

    private LoggingHttpEnvironmentPostProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new LoggingHttpEnvironmentPostProcessor();
    }

    @Test
    @DisplayName("Test post process environment with valid true property")
    void testPostProcessEnvironmentWithValidTrueProperty() {

        when(environment.getProperty("logging.http.enabled")).thenReturn("true");

        processor.postProcessEnvironment(environment, application);

        verify(environment, Mockito.times(1)).getProperty("logging.http.enabled");

    }

    @Test
    @DisplayName("Test post process environment with valid false property")
    void testPostProcessEnvironmentWithValidFalseProperty() {

        when(environment.getProperty("logging.http.enabled")).thenReturn("false");

        processor.postProcessEnvironment(environment, application);

        verify(environment, Mockito.times(1)).getProperty("logging.http.enabled");
    }

    @Test
    @DisplayName("Test post process environment with invalid property")
    void testPostProcessEnvironmentWithInvalidProperty() {

        when(environment.getProperty("logging.http.enabled")).thenReturn("invalid");

        assertThrows(LoggingStartupHttpException.class,
                () -> processor.postProcessEnvironment(environment, application));

        verify(environment, Mockito.times(1)).getProperty("logging.http.enabled");
    }
}