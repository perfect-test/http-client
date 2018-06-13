package com.perfecttest.http;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by Sidelnikov Mikhail on 19.02.15. Response entity for http requests
 */
public class HttpResponseEntity {
    private int code;
    private String responseString;
    private String url;
    private List<RequestHeader> headers;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseString() {
        return responseString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public void setHeaders(List<RequestHeader> headers) {
        this.headers = headers;
    }

    public List<RequestHeader> getHeaders() {
        return headers;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Url : ").append(url).append("\n");
        result.append("Code : ").append(code).append("\n");
        if (headers != null && !headers.isEmpty()) {
            result.append("Headers : ").append("\n");
            for (RequestHeader header : headers) {
                result.append("\t").append(header.getName()).append(" : ").append(header.getValue()).append("\n");
            }
        }
        if (responseString != null && !responseString.isEmpty()) {
            result.append("Body : ").append("\n").append("\t").append(responseString);
        }
        return result.toString();
    }

    public HttpResponseEntity checkCode(int statusCode) {
        assertTrue(code == statusCode, "Response code(" + code + ") not equals to expected(" + statusCode + ")");
        return this;
    }

}
