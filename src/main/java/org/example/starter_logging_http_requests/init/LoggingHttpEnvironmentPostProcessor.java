package org.example.starter_logging_http_requests.init;

import lombok.extern.slf4j.Slf4j;
import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class LoggingHttpEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        log.info("Вызов LoggingHttpEnvironmentPostProcessor");
        String enabledPropertyValue = environment.getProperty("logging.http.enabled");
        boolean isBoolValue = Boolean.TRUE.toString().equalsIgnoreCase(enabledPropertyValue) ||
                Boolean.FALSE.toString().equalsIgnoreCase(enabledPropertyValue);

        if (!isBoolValue) {
            throw new LoggingStartupHttpException
                    ("Ошибка при проверке свойства 'http.logging.enabled'");
        }
    }
}
