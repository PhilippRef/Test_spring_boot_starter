package org.example.starter_logging_http_requests.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect класс для логирования
 */

@Component
@Aspect
@Order(1)
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

//    @Pointcut("execution(* org.example.starter_logging_http_requests.filter.LoggingHttpInterceptor.*(..))")
//    public void loggingHttpInterceptorPointcut() {
//    }
//
//    @Before("loggingHttpInterceptorPointcut()")
//    public void beforeCallLoggingHttpInterceptor(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//
//        log.info("Выполнение метода {} с аргументами {}", methodName, args);
//    }
//
//    @After("loggingHttpInterceptorPointcut()")
//    public void afterCallLoggingHttpInterceptor(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//
//        log.info("Метод {} выполнился с аргументами {}", methodName, args);
//    }
//}


    /**
     * Метод позволяет логировать поведение в классе LoggingHttpInterceptor
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */

    @Around("bean(loggingHttpInterceptor)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        log.info("Выполнение метода {} с аргументами {}", methodName, args);

        Object result = proceedingJoinPoint.proceed(args);

        long duration = System.currentTimeMillis() - startTime;

        log.info("Метод {} с аргументами {} выполнился за {} мс",
                methodName, args, duration);

        return result;
    }
}

