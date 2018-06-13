package com.perfecttest.http;

/**
 * Created by Sidelnikov Mikhail on 27.02.15.
 * url request parameter
 */
public class RequestParameter {
    private String name;
    private String value;

    public RequestParameter() {
    }

    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public RequestParameter(String name, int value) {
        this.name = name;
        this.value = Integer.toString(value);
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

    public RequestParameter withName(String name) {
        this.name = name;
        return this;
    }

    public RequestParameter withValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestParameter parameter = (RequestParameter) o;

        return name != null ? name.equals(parameter.name) : parameter.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String toString() {
        return "Name: " + name + "; value: " + value;
    }
}
