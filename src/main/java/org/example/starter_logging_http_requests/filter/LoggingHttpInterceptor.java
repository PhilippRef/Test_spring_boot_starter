package org.example.starter_logging_http_requests.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.example.starter_logging_http_requests.model.LogStructureRequest;
import org.example.starter_logging_http_requests.model.LogStructureResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для перехвата запросов и ответов от клиента
 */

@Component
public class LoggingHttpInterceptor implements HandlerInterceptor {

    private static final Logger log = LogManager.getLogger(LoggingHttpInterceptor.class);

    /**
     * Этот метод перехватывает любой запрос до того, как он попадёт в целевой метод контроллера
     *
     * @param request  - представляет собой обрабатываемый запрос
     * @param response - представляет собой обрабатываемый ответ
     * @param handler  - целевой метод контроллера, который будет обрабатывать этот запрос
     * @return - если true, то запрос обрабатывается; если false, то целевой метод контроллера не вызывается, запрос будет остановлен
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
//        log.info("Начало выполнения метода preHandle");

        try {
            String requestMethodType = request.getMethod();
            String url = request.getRequestURI();
            Map<String, String> requestHeader = getHeadersRequest(request);

            long processingTime = System.currentTimeMillis() - startTime;

            LogStructureRequest logStructureRequest = getLogStructureRequest(requestMethodType,
                    url, requestHeader, processingTime);

            log.info(logStructureRequest.toString());

        } catch (Exception ex) {
            throw new LoggingStartupHttpException("Ошибка при выполнении метода preHandle: " + ex.getMessage());
        }
        return true;
    }

    /**
     * Этот метод выполняется после того, как выполнится целевой метод контроллера, но до отправки ответа клиенту
     *
     * @param request      - см. выше
     * @param response     - см. выше
     * @param handler      - см. выше
     * @param modelAndView - содержит данные о модели представления, которые визуализирует клиент
     * @throws Exception
     */

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("Начало выполнения метода postHandle");
//        long startTime = (Long) request.getAttribute("startTime");
//
//
//        log.info("Время выполнения: {} мс", System.currentTimeMillis() - startTime);
//    }

    /**
     * Метод выполняется после завершения механизма запроса и ответа
     *
     * @param request  - см. выше
     * @param response - см. выше
     * @param handler  - см. выше
     * @param ex       - объект исключения, который содержит информацию об ошибке
     * @throws Exception
     */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (ex != null) {
            throw new LoggingStartupHttpException("Ошибка при выполнении метода afterCompletion: " +
                    ex.getMessage() + ", URL: " + request.getRequestURI());
        }
        try {
//            log.info("Начало выполнения метода afterCompletion");
            long startTime = (Long) request.getAttribute("startTime");

            int responseCode = response.getStatus();
            Map<String, String> responseHeader = getHeadersResponse(response);

            long processingTime = System.currentTimeMillis() - startTime;

            LogStructureResponse logStructureResponse =
                    getLogStructureResponse(responseCode, responseHeader, processingTime);

            log.info(logStructureResponse.toString());

        } catch (Exception e) {
            throw new LoggingStartupHttpException("Ошибка при выполнении метода afterCompletion: "
                    + e.getMessage());
        }

    }

    /**
     * Метод для получения заголовков запроса
     *
     * @param request - см. выше
     * @return Map<String, String>
     */

    private Map<String, String> getHeadersRequest(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            headers.put(headerName, request.getHeader(headerName));
        });

        return headers;
    }

    /**
     * Метод для получения заголовков ответа
     *
     * @param response - см. выше
     * @return Map<String, String>
     */

    private Map<String, String> getHeadersResponse(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();

        response.getHeaderNames().forEach(headerName -> {
            headers.put(headerName, response.getHeader(headerName));
        });

        return headers;
    }

    /**
     * Метод для создания объекта (запрос) LogStructureRequest
     *
     * @param requestMethodType - тип запроса (POST, GET и т.д.)
     * @param url               - адрес запроса
     * @param requestHeader     - заголовки запроса
     * @param processingTime    - время обработки запроса
     * @return объект LogStructure
     */

    private LogStructureRequest getLogStructureRequest(String requestMethodType, String url,
                                                       Map<String, String> requestHeader,
                                                       long processingTime) {
        LogStructureRequest logStructureRequest = new LogStructureRequest();

        logStructureRequest.setRequestMethodType(requestMethodType);
        logStructureRequest.setUrl(url);
        logStructureRequest.setRequestHeader(requestHeader);
        logStructureRequest.setProcessingTime(processingTime);

        return logStructureRequest;
    }


    /**
     * Метод для создания объекта (ответ) LogStructureRequest
     *
     * @param statusCode     - код ответа
     * @param responseHeader - заголовки ответа
     * @param processingTime - время обработки запроса
     * @return объект LogStructure
     */
    private LogStructureResponse getLogStructureResponse(int statusCode,
                                                         Map<String, String> responseHeader,
                                                         long processingTime) {
        LogStructureResponse logStructureResponse = new LogStructureResponse();

        logStructureResponse.setStatusCode(statusCode);
        logStructureResponse.setResponseHeader(responseHeader);
        logStructureResponse.setProcessingTime(processingTime);

        return logStructureResponse;
    }
}
