package org.example.starter_logging_http_requests.orderApplication.service;

import org.example.starter_logging_http_requests.orderApplication.entity.Order;
import org.example.starter_logging_http_requests.orderApplication.exception.OrderApplicationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервисный класс (логика) для работы с заказами
 */

@Service
public class OrderService {

    private static final Map<String, Order> orders = new HashMap<>();

    static {
        orders.put("user1", new Order(1, "Pizza", "CREATED", "user1"));
        orders.put("user2", new Order(2, "Hamburger", "IN_PROGRESS", "user2"));
        orders.put("user3", new Order(3, "Fries chicken", "COMPLETED", "user3"));
    }

    /**
     * Метод добавления нового заказа для существующего пользователя. Если пользователь не найден, выбрасывается исключение OrderApplicationException с соответствующим сообщением
     * @param order
     * @return Order
     */

    public Order addOrder(Order order) {
        orders.put(order.getOrderUserName(), order);
        return orders.get(order.getOrderUserName());
    }

    /**
     * Метод обновления существующего заказа. Если пользователь или заказ не найдены, выбрасывается исключение OrderApplicationException с соответствующим сообщением
     * @param order
     * @return Order
     */

    public Order updateOrder(Order order) {
        Order foundOrder = getOrderByUserName(order.getOrderUserName());
        String userName = foundOrder.getOrderUserName();
        getOrderByOrderId(order.getOrderId());

        orders.put(userName, order);

        return orders.get(userName);
    }

    /**
     * Метод для получения заказа по имени пользователя. Если пользователь не найден, выбрасывается исключение OrderApplicationException с соответствующим сообщением
     * @param userName - имя пользователя
     * @return Order
     */

    public Order getOrderByUserName(String userName) {
        if (!orders.containsKey(userName)) {
            throw new OrderApplicationException("Пользователь " + userName + " не найден");
        }
        return orders.get(userName);
    }

    /**
     * Метод для получения заказа по id. Если заказ не найден, выбрасывается исключение OrderApplicationException с соответствующим сообщением
     * @param orderId - номер заказа
     * @return Order
     */

    public Order getOrderByOrderId(int orderId) {
        return orders
                .values()
                .stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElseThrow(() ->
                        new OrderApplicationException("Заказ " + orderId + " не найден"));
    }

    /**
     * Метод для получения всех заказов
     * @return List<Order>
     */

    public List<Order> getAllOrders() {
        return List.copyOf(orders.values());
    }

    /**
     * Метод для удаления заказа. Если пользователь или заказ не найдены, выбрасывается исключение OrderApplicationException с соответствующим сообщением
     * @param userName - имя пользователя
     * @param orderId - номер заказа
     * @return
     */

    public String removeOrder(String userName, int orderId) {
        getOrderByUserName(userName);
        getOrderByOrderId(orderId);
        orders.remove(userName);
        return "Заказ: " + orderId + " удален у пользователя " + userName;
    }

}
