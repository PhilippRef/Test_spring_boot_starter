package org.example.starter_logging_http_requests.orderApplication.exception;

import org.example.starter_logging_http_requests.annotation.Throw;

@Throw
public class OrderApplicationException extends RuntimeException {
    public OrderApplicationException() {

    }

    public OrderApplicationException(String message) {
        super(message);
    }
}
