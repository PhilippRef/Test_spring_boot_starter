package org.example.starter_logging_http_requests.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Класс для хранения структуры лога запроса
 */

@Setter
@Getter
public class LogStructureRequest {
    private String requestMethodType;
    private String url;
    private Map<String, String> requestHeader;
    private long processingTime;

    @Override
    public String toString() {
        return "LogStructureRequest {" + "\n" +
                "requestMethodType = " + requestMethodType + "\n" +
                "url = " + url + "\n" +
                "processingTime = " + processingTime + " ms" + "\n" +
                "requestHeader = " + requestHeader + "\n" + "}";
    }
}
