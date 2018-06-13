package com.perfecttest.http;

/**
 * Created by mikesidelnikov on 31.01.17.
 * body parameter for POST
 */
public class RequestBodyParameter {


    private String name;
    private String value;

    public RequestBodyParameter() {
    }

    public RequestBodyParameter(String name, String value) {
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

    public RequestBodyParameter withName(String name) {
        this.name = name;
        return this;
    }

    public RequestBodyParameter withValue(String value) {
        this.value = value;
        return this;
    }

    public String toString() {
        return "Name: " + name + "; value: " + value;
    }

}
