package com.perfecttest.libs.example;

import com.google.gson.GsonBuilder;

public class ExampleResponse {
    private int code;
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ExampleResponse(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public ExampleRequestValidator check(){
        return new ExampleRequestValidator(this);
    }

    public ExampleResponseAsObject getResponseObject(){
        return new GsonBuilder().create().fromJson(body, ExampleResponseAsObject.class);
    }
}
