package org.example.starter_logging_http_requests.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.starter_logging_http_requests.exception.LoggingStartupHttpException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoggingHttpInterceptorTest {

    private LoggingHttpInterceptor loggingHttpInterceptor;

    private final HttpServletRequest request =
            Mockito.mock(HttpServletRequest.class);

    private final Object handler =
            Mockito.mock(Object.class);

    private final HttpServletResponse response =
            Mockito.mock(HttpServletResponse.class);

    @BeforeEach
    void setUp() {
        loggingHttpInterceptor = new LoggingHttpInterceptor();
    }

    @Test
    @DisplayName("Test preHandle")
    void testPreHandle() throws Exception {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("api/test");

        List<String> headerNames = new ArrayList<>();
        headerNames.add("user-agent");
        headerNames.add("content-type");
        headerNames.add("connection");

        when(request.getHeaderNames()).thenReturn(Collections.enumeration(headerNames));
        when(request.getHeader("user-agent")).thenReturn("Chrome");
        when(request.getHeader("content-type")).thenReturn("application/json");
        when(request.getHeader("connection")).thenReturn("keep-alive");

        boolean result = loggingHttpInterceptor.preHandle(request, response, handler);

        assertTrue(result);

        verify(request, Mockito.times(1)).getMethod();
        verify(request, Mockito.times(1)).getRequestURI();

        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            if ("user-agent".equals(headerName)) {
                assertEquals("Chrome", request.getHeader(headerName));
            }
            if ("Content-Type".equals(headerName)) {
                assertEquals("application/json", request.getHeader(headerName));
            }
            if ("Connection".equals(headerName)) {
                assertEquals("keep-alive", request.getHeader(headerName));
            }
        }
    }

    @Test
    @DisplayName("Test preHandle with exception")
    void testPreHandleWithException() throws Exception {
        when(request.getMethod())
                .thenThrow((new LoggingStartupHttpException
                        ("Test preHandleException")));

        try {
            loggingHttpInterceptor.preHandle(request, response, handler);
        } catch (LoggingStartupHttpException e) {
            assertEquals("Ошибка при выполнении метода preHandle:" +
                    " Test preHandleException", e.getMessage());
        }

        verify(request, Mockito.times(1)).getMethod();

    }

    @Test
    @DisplayName("Test afterCompletion")
    void testAfterCompletion() throws Exception {
        when(response.getStatus()).thenReturn(200);
        when(request.getAttribute("startTime")).thenReturn(System.currentTimeMillis());

        List<String> responseHeaderNames = new ArrayList<>();
        responseHeaderNames.add("transfer-encoding");
        responseHeaderNames.add("content-type");
        responseHeaderNames.add("connection");

        when(response.getHeaderNames()).thenReturn(responseHeaderNames);
        when(response.getHeader("transfer-encoding")).thenReturn("chunked");
        when(response.getHeader("content-type")).thenReturn("application/json");
        when(response.getHeader("connection")).thenReturn("keep-alive");

        loggingHttpInterceptor.afterCompletion(request, response, handler, null);

        assertEquals(200, response.getStatus());

        verify(response, Mockito.times(2)).getStatus();

        response.getHeaderNames().forEach(headerName -> {
            if ("transfer-encoding".equals(headerName)) {
                assertEquals("chunked", response.getHeader(headerName));
            }
            if ("content-type".equals(headerName)) {
                assertEquals("application/json", response.getHeader(headerName));
            }
            if ("connection".equals(headerName)) {
                assertEquals("keep-alive", response.getHeader(headerName));
            }
        });
    }
}