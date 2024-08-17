package org.example.starter_logging_http_requests.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Класс для конфигурации свойств. Используется для добавления свойств в конфигурационный класс LoggingHttpAutoConfiguration
 */

@ConfigurationProperties(prefix = "logging.http")
@Data
public class LoggingHttpProperties {
    private Boolean enabled;
}
