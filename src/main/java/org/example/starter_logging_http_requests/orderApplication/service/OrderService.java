package org.example.starter_logging_http_requests.orderApplication.service;

import org.example.starter_logging_http_requests.orderApplication.entity.Order;
import org.example.starter_logging_http_requests.orderApplication.exception.OrderApplicationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final Map<String, Order> orders = Map.of(
            "user1", new Order(1, "Pizza", "CREATED", "user1"),
            "user2", new Order(2, "Hamburger", "IN_PROGRESS", "user2"),
            "user3", new Order(3, "Fries chicken", "COMPLETED", "user3")
    );

    public Order addOrder(String userName, Order order) {
        return orders.put(userName, order);
    }

    public Order updateOrder(String userName, Order order) {
        getOrderByUserName(userName);
        getOrderByOrderId(order.getOrderId());

        return orders.put(userName, order);
    }

    public Order getOrderByUserName(String userName) {
        if (!orders.containsKey(userName)) {
            throw new OrderApplicationException("Пользователь " + userName + " не найден");
        }
        return orders.get(userName);
    }

    public Order getOrderByOrderId(int orderId) {
        return orders
                .values()
                .stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElseThrow(() ->
                        new OrderApplicationException("Заказ " + orderId + " не найден"));
    }

    public List<Order> getAllOrders() {
        return List.copyOf(orders.values());
    }

    public String removeOrder(String userName, int orderId) {
        getOrderByOrderId(orderId);
        getOrderByUserName(userName);
        orders.remove(userName);
        return "Заказ: " + orderId + " удален у пользователя " + userName;
    }

}
