package org.example.starter_logging_http_requests.init;

import lombok.extern.slf4j.Slf4j;
import org.example.starter_logging_http_requests.model.LogStructureRequest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class LoggingHttpApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("Вызов LoggingHttpApplicationContextInitializer");
        applicationContext.getBeanFactory()
                .registerSingleton(LogStructureRequest.class.getName(), new LogStructureRequest());

    }
}
