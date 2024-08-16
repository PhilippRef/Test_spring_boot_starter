package org.example.starter_logging_http_requests.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(4)
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    /**
     * Метод позволяет логировать поведение в классе LoggingHttpInterceptor
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */

//    @Around("execution(* org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor.*(..)) ||" +
//            "execution(* org.example.starter_logging_http_requests.config.LoggingHttpAutoConfiguration.*(..)) ||" +
//            "execution(* org.example.starter_logging_http_requests.init..*(..))")
    @Around("bean(loggingHttpInterceptor)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, args);

        Object result = proceedingJoinPoint.proceed(args);

        long duration = System.currentTimeMillis() - startTime;

        log.info("Метод {} выполнился за {} мс",
                methodName, duration);

        return result;
    }
}
