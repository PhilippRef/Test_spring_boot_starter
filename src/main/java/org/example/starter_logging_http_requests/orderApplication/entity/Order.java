package org.example.starter_logging_http_requests.orderApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private int orderId;
    private String description;
    private String status;
    private String orderUserName;
}
