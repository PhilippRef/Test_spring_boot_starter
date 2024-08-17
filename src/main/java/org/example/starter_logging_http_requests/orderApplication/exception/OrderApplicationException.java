package org.example.starter_logging_http_requests.orderApplication.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.starter_logging_http_requests.annotation.Throw;

/**
 * Класс для вызова исключений в orderApplication
 */

@Throw
public class OrderApplicationException extends RuntimeException {

    private static final Logger log = LogManager.getLogger(OrderApplicationException.class);

    public OrderApplicationException() {

    }

    /**
     * Конструктор вызывается при возникновении исключений в orderApplication
     * Пример вывода: ERROR [http-nio-8080-exec-1] o.e.s.o.e.OrderApplicationException.<init>(18): Заказ 10 не найден
     * @param message
     */

    public OrderApplicationException(String message) {
        super(message);
        log.error(message);
    }
}
