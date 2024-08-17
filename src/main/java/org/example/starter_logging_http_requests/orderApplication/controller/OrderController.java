package org.example.starter_logging_http_requests.orderApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.starter_logging_http_requests.annotation.SuccessLogging;
import org.example.starter_logging_http_requests.orderApplication.entity.Order;
import org.example.starter_logging_http_requests.orderApplication.exception.OrderApplicationException;
import org.example.starter_logging_http_requests.orderApplication.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с заказами
 */

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
@SuccessLogging
public class OrderController {

    private final OrderService orderService;

    /**
     * Метод GET запроса на получение заказа по id
     * Пример запроса: GET http://localhost:8080/api/order/1
     * Пример ответа:
     * {
     *     "orderId": 1,
     *     "description": "Pizza",
     *     "status": "CREATED",
     *     "orderUserName": "user1"
     * }
     * Пример запроса на несуществующий id заказа: GET http://localhost:8080/api/order/5
     * Пример ответа:
     * Заказ 5 не найден
     * @param id - id заказа
     * @return ResponseEntity
     */

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByOrderId(id));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Метод GET запроса на все заказы.
     * Пример запроса: GET http://localhost:8080/api/order/
     * Пример ответа:
     * [
     *     {
     *         "orderId": 1,
     *         "description": "Pizza",
     *         "status": "CREATED",
     *         "orderUserName": "user1"
     *     },
     *     {
     *         "orderId": 2,
     *         "description": "Hamburger",
     *         "status": "IN_PROGRESS",
     *         "orderUserName": "user2"
     *     },
     *     {
     *         "orderId": 3,
     *         "description": "Fries chicken",
     *         "status": "COMPLETED",
     *         "orderUserName": "user3"
     *     }
     * ]
     * @return ResponseEntity
     */

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    /**
     * Метод GET запроса на заказ у пользователя по его имени.
     * Пример запроса: GET http://localhost:8080/api/order/user/user1
     * Пример ответа:
     * {
     *     "orderId": 1,
     *     "description": "Pizza",
     *     "status": "CREATED",
     *     "orderUserName": "user1"
     * }
     * Пример запроса на несуществующее имя пользователя: GET http://localhost:8080/api/order/user/user5
     * Пример ответа:
     * Пользователь user5 не найден
     * @param name - имя пользователя
     * @return ResponseEntity
     */

    @GetMapping("/user/{name}")
    public ResponseEntity<?> getOrdersByUserName(@PathVariable String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(orderService.getOrderByUserName(name));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Метод POST запроса на создание заказа
     * Пример запроса: POST http://localhost:8080/api/order/
     * {
     *     "name": "NewUser",
     *     "orderId": "5",
     *     "description": "Pizza",
     *     "status": "CREATED",
     *     "orderUserName": "NewUser"
     * }
     * Пример ответа:
     * {
     *     "orderId": 5,
     *     "description": "Pizza",
     *     "status": "CREATED",
     *     "orderUserName": "NewUser"
     * }
     * @param order - объект Order
     * @return ResponseEntity
     */

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.addOrder(order));
    }

    /**
     * Метод PUT для обновления заказа
     * Пример запроса: PUT http://localhost:8080/api/order/
     * {
     *         "orderId": 1,
     *         "description": "Pizza",
     *         "status": "CANCELLED",
     *         "orderUserName": "user1"
     * }
     * Пример ответа:
     * {
     *     "orderId": 1,
     *     "description": "Pizza",
     *     "status": "CANCELLED",
     *     "orderUserName": "user1"
     * }
     * @param order - объект Order
     * @return ResponseEntity
     */

    @PutMapping("/")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(orderService.updateOrder(order));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Метод DELETE для удаления заказа
     * Пример запроса: DELETE http://localhost:8080/api/order/user1/1
     * Пример ответа: Заказ: 1 удален у пользователя user1
     * Пример запроса на несуществующий заказ у существующего пользователя: DELETE http://localhost:8080/api/order/user1/10
     * Пример ответа: Заказ 10 не найден
     * Пример запроса на несуществующего пользователя: DELETE http://localhost:8080/api/order/user10/1
     * Пример ответа: Пользователь user10 не найден
     * @param name - имя пользователя
     * @param id - id заказа
     * @return ResponseEntity
     */

    @DeleteMapping("/{name}/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String name, @PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.removeOrder(name, id));
        } catch (OrderApplicationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
