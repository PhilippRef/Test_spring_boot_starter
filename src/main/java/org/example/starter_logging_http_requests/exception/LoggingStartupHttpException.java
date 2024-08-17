package org.example.starter_logging_http_requests.exception;

import org.example.starter_logging_http_requests.annotation.Throw;

/**
 * Класс для вызова исключений
 */

@Throw
public class LoggingStartupHttpException extends RuntimeException {

    public LoggingStartupHttpException() {
    }

    /**
     * Конструктор вызывается при возникновении исключения
     * @param message
     */

    public LoggingStartupHttpException(String message) {
        super(message);
    }
}
