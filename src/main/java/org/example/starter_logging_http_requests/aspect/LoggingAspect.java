package org.example.starter_logging_http_requests.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(4)
@Slf4j
public class LoggingAspect {

    @Around("execution")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        log.info("Вызванный метод: {}", proceedingJoinPoint.getSignature().toShortString());

        Object result = proceedingJoinPoint.proceed();

        long duration = System.currentTimeMillis() - startTime;

        log.info("Время выполнения метода {} за {} мс",
                proceedingJoinPoint.getSignature().toShortString(), duration);

        return result;
    }
}
