package org.example.starter_logging_http_requests.init;

import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * Класс перехватывает исключения, которые могут возникнуть при старте приложения
 */

public class LoggingHttpFailureAnalyzer extends
        AbstractFailureAnalyzer<LoggingStartupHttpException> {

    /**
     * Метод, перехватывает исключение и выводит данные о возможных причинах возникновения.
     * @param rootFailure - исключение для передачи в метод анализатора
     * @param cause - причина возникновения
     * @return объект FailureAnalysis
     */

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, LoggingStartupHttpException cause) {
        return new FailureAnalysis(cause.getMessage(),
                "Укажите верные значения для свойства в файле конфигурации." +
                        "Допустимые значения: true или false.", cause);
    }
}
