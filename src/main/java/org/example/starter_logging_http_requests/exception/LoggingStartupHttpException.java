package org.example.starter_logging_http_requests.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.starter_logging_http_requests.annotation.Throw;

/**
 * Класс для вызова исключений
 */

@Throw
public class LoggingStartupHttpException extends RuntimeException {

    private static final Logger log = LogManager.getLogger(LoggingStartupHttpException.class);

    public LoggingStartupHttpException() {
    }

    /**
     * Конструктор вызывается при возникновении исключения
     *
     * @param message
     */

    public LoggingStartupHttpException(String message) {
        super(message);
        log.error(message);
    }
}
