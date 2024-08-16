package org.example.starter_logging_http_requests.exception;

import org.example.starter_logging_http_requests.annotation.Throw;

@Throw
public class LoggingStartupHttpException extends RuntimeException {
    public LoggingStartupHttpException() {
    }

    public LoggingStartupHttpException(String message) {
        super(message);
    }
}
