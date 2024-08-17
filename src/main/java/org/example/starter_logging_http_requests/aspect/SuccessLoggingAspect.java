package org.example.starter_logging_http_requests.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Класс для логирования успешного выполнения методов
 */

@Component
@Aspect
@Order(1)
public class SuccessLoggingAspect {

    private static final Logger log = LogManager.getLogger(SuccessLoggingAspect.class);

    @AfterReturning("within(org.example.starter_logging_http_requests.orderApplication.controller.OrderController) &&" +
            "@within(org.example.starter_logging_http_requests.annotation.SuccessLogging)")
    public void afterReturning(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();

        log.info("Метод {} успешно выполнился с аргументами: {}", methodName, args);
    }
}
