package com.perfecttest.http;


import org.apache.http.entity.ContentType;

import java.util.List;

/**
 * Created by Sidelnikov Mikhail on 06.07.15.
 * Generator for curl
 */
public class CurlGenerator {
    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type: ";

    public static String getCurl(HttpRequest request, HttpRequestMethod method) {
        StringBuilder curl = new StringBuilder("curl -v ");
        curl.append("-X ").append(method.getMethodName()).append(" ");
        curl.append("'").append(HttpUtils.getUrlWithParams(request.getUrl(), request.getParameters())).append("'").append(" ");
        curl.append(getHeadersString(request));
        curl.append(getCookiesString(request.getCookies()));
        curl.append(getBodyString(request.getBody()));
        curl.append(getBodyParamsString(request.getBodyParameters()));
        return curl.toString();
    }

    private static String getBodyParamsString(List<RequestBodyParameter> bodyParameters) {
        if (bodyParameters != null && !bodyParameters.isEmpty()) {
            StringBuilder builder = new StringBuilder(" -d '");
            for (RequestBodyParameter bodyParameter : bodyParameters) {
                builder.append(bodyParameter.getName()).append("=").append(bodyParameter.getValue()).append("&");
            }
            builder.setLength(builder.length() - 1);
            builder.append("' ");
            return builder.toString();
        }
        return "";
    }

    private static String getBodyString(String body) {
        if (body != null && !body.isEmpty()) {
            return new StringBuilder("-d '").append(body).append("' ").toString();
        }
        return "";
    }

    private static String getCookiesString(List<RequestCookie> cookies) {
        StringBuilder result = new StringBuilder();
        if (cookies != null && !cookies.isEmpty()) {
            result.append(" -b ").append("'");
            for (RequestCookie cookie : cookies) {
                result.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
            }
            result.setLength(result.length() - 1);
            result.append("'").append(" ");
        }
        return result.toString();
    }

    private static String getHeadersString(HttpRequest request) {
        StringBuilder result = new StringBuilder();
        List<RequestHeader> headersList = request.getHeaders();

        if (headersList != null && !headersList.isEmpty()) {
            for (RequestHeader header : headersList) {
                result.append(" -H ").append("'").append(header.getName()).append(": ").append(header.getValue()).append("' ");
            }
        }
        ContentType contentType = request.getContentType();
        if (contentType != null) {
            result.append(" -H ").append("'");
            result.append(CONTENT_TYPE_HEADER_NAME).append(contentType.toString());
            result.append("'").append(" ");
        }
        return result.toString();
    }

}
