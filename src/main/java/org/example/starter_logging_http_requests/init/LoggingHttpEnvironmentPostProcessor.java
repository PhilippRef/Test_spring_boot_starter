package org.example.starter_logging_http_requests.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Класс позволяет настраивать среду приложения (его окружение) перед запуском приложения, применяя различные свойства
 */

public class LoggingHttpEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final Logger log = LogManager.getLogger(LoggingHttpEnvironmentPostProcessor.class);

    /**
     * Метод, который занимается настройкой окружения (постобработка заданной среды)
     * @param environment - среда окружения
     * @param application - приложение, к которому принадлежит среда окружения
     */

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
