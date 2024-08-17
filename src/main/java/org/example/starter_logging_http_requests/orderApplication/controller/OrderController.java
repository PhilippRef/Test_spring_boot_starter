package org.example.starter_logging_http_requests.orderApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.starter_logging_http_requests.orderApplication.entity.Order;
import org.example.starter_logging_http_requests.orderApplication.exception.OrderApplicationException;
import org.example.starter_logging_http_requests.orderApplication.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByOrderId(id));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<?> getOrdersByUserName(String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(orderService.getOrderByUserName(name));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody String userName,
                                             @RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.addOrder(userName, order));
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateOrder(@PathVariable String userName,
                                         @RequestBody Order order) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(orderService.updateOrder(userName, order));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{name}/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String name, @PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.removeOrder(name, id));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
