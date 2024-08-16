package org.example.starter_logging_http_requests.init;

import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class LoggingHttpFailureAnalyzer extends
        AbstractFailureAnalyzer<LoggingStartupHttpException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, LoggingStartupHttpException cause) {
        return new FailureAnalysis(cause.getMessage(),
                "Укажите верные значения для свойства в файле конфигурации." +
                        "Допустимые значения: true или false.", cause);
    }
}
