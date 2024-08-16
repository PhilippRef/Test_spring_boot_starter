package org.example.starter_logging_http_requests.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class LogStructureRequest {
    private String requestMethodType;
    private String url;
    private Map<String, String> requestHeader;
    private long processingTime;

}
