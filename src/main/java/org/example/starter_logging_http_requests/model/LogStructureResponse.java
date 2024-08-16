package org.example.starter_logging_http_requests.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@ToString
public class LogStructureResponse {
    private int statusCode;
    private Map<String, String> responseHeader;
    private long processingTime;
}
