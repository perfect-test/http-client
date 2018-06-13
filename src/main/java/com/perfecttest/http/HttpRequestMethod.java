package com.perfecttest.http;

/**
 * Created by Sidelnikov Mikhail on 04.07.15.
 * http request methods
 */
public enum HttpRequestMethod {
    GET("GET"),
    POST("POST"),
    MULTIPART_POST("MULTIPART_POST"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    PUT("PUT");

    private String methodName;

    HttpRequestMethod(String name) {
        this.methodName = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public static HttpRequestMethod getByName(String name){
        for(HttpRequestMethod method : values()){
            if(method.getMethodName().equals(name)){
                return method;
            }
        }
        return null;
    }
}
