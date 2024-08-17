Это приложение - кастомный Spring Boot Starter, который уменьшает количество загружаемых starter'ов, ускоряет загрузку приложения.
Позволяет логировать входящие и исходящие HTTP запросы и ответы.
Логирование включает в себя: метод запроса, URL, заголовки запроса и ответа, код ответа, время обработки.

Реализована возможность записи логов с помощью log4j2. Папка для записи по умолчанию /logs, файл app.log.
Управление включением и отключением логирование HTTP запросов и ответов реализовано в application.properties:
logging.http.enabled=true - включает логирование.
logging.http.enabled=false - отключает логирование.

Для тестирования Spring Boot Starter реализовано приложение orderApplication.
После запуска приложения, в orderApplication.service.OrderService производится наполнение данными для тестирования:

("user1", new Order(1, "Pizza", "CREATED", "user1"));

("user2", new Order(2, "Hamburger", "IN_PROGRESS", "user2"));

("user3", new Order(3, "Fries chicken", "COMPLETED", "user3"));

Для старта приложения необходимо запустить StarterLoggingHttpRequestsApplication.

orderApplication endPoints:

Пример запроса: GET http://localhost:8080/api/order/1 - метод GET запроса на получение заказа по id.

Пример запроса: GET http://localhost:8080/api/order/ - метод GET запроса на получение всех заказов.

Пример запроса: GET http://localhost:8080/api/order/user/user1 - метод GET запроса на получение заказа у пользователя по его имени.

Пример запроса: POST http://localhost:8080/api/order/ - метод POST запроса на создание заказа.

Пример запроса: PUT http://localhost:8080/api/order/ - метод PUT для обновления заказа.

Пример запроса: DELETE http://localhost:8080/api/order/user1/1 - метод DELETE для удаления заказа.

Примеры ответов приложения вы найдете в описании к соответствующим методам.

Пример логирование входящего запроса:

LogStructureRequest {

requestMethodType = GET

url = /api/order/

processingTime = 1 ms

requestHeader = {postman-token=some token, host=localhost:8080, connection=keep-alive, accept-encoding=gzip, deflate, br, user-agent=PostmanRuntime/7.41.1, accept=*/*}
}

Пример логирования исходящего ответа:

LogStructureResponse {

statusCode = 200

processingTime = 83 ms

responseHeader = {Transfer-Encoding=chunked, Keep-Alive=timeout=60, Connection=keep-alive, Date=Sat, 17 Aug 2024 13:13:49 GMT, Content-Type=application/json}
}





