package org.example.starter_logging_http_requests.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Класс для хранения структуры лога ответа
 */

@Setter
@Getter
public class LogStructureResponse {
    private int statusCode;
    private Map<String, String> responseHeader;
    private long processingTime;

    @Override
    public String toString() {
        return "LogStructureResponse {" + "\n" +
                "statusCode = " + statusCode + "\n" +
                "processingTime = " + processingTime + " ms" + "\n" +
                "responseHeader = " + responseHeader + "\n" + "}";

    }
}
