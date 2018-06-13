package com.perfecttest.http;

/**
 * Created by Sidelnikov Mikhail on 27.02.15.
 * request header
 */
public class RequestHeader {
    private String name;
    private String value;

    public RequestHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Name: " + name + "; value: " + value;
    }

}
