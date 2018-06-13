package com.perfecttest.http.logger.impl;

import com.perfecttest.http.*;
import com.perfecttest.http.logger.IRequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Sidelnikov Mikhail on 04.07.15.
 * Console logger for http requests
 */
public class ConsoleRequestLogger implements IRequestLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleRequestLogger.class);

    public void log(HttpRequest request, HttpRequestMethod requestMethod) {
        StringBuilder result = new StringBuilder();
        String url = HttpUtils.getUrlWithParams(request.getUrl(), request.getParameters());
        result.append("Url : ").append(url).append("\n");
        result.append("Method : ").append(requestMethod.getMethodName()).append("\n");
        result.append("cURL: ").append(CurlGenerator.getCurl(request, requestMethod)).append("\n");
        List<RequestCookie> cookies = request.getCookies();
        if (cookies != null && !cookies.isEmpty()) {
            result.append("Cookies : ").append("\n");
            for (RequestCookie cookie : cookies) {
                result.append("\t").append(cookie.getName()).append(" : ").append(cookie.getValue()).append("\n");
            }
        }
        List<RequestHeader> headers = request.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            result.append("Headers : ").append("\n");
            for (RequestHeader header : headers) {
                result.append("\t").append(header.getName()).append(" : ").append(header.getValue()).append("\n");
            }
        }
        if (request.getContentType() != null) {
            result.append("Content-Type").append(" : ").append(request.getContentType().toString()).append("\n");
        }

        String body = request.getBody();
        if (body != null) {
            result.append("Body : ").append("\n");
            result.append("\t").append(body);
        }
        LOGGER.info(result.toString());
    }
}
