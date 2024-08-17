package org.example.starter_logging_http_requests.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Класс для перехвата исключений
 */

@Component
@Aspect
@Order(1)
public class ExceptionHandlerAspect {

    private static final Logger log = LogManager.getLogger(ExceptionHandlerAspect.class);

    /**
     * Метод ловит возникающие исключения в методах в orderApplication.controller
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "within(org.example.starter_logging_http_requests.orderApplication.controller.*) && " +
            "execution(* * (..)) throws com.org.example.starter_logging_http_requests.exception.Throw *",
            throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        log.error("Произошла ошибка при вызове метода: {}", joinPoint.getSignature()
                .toLongString());
        log.error("Ошибка: {}", e.getMessage());
    }
}
