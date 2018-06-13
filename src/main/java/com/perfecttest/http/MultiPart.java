package com.perfecttest.http;

/**
 * Created by Sidelnikov Mikhail on 01.09.15.
 * Object for multi part
 */
public class MultiPart {
    private String name;
    private String value;

    public MultiPart(String name, String value) {
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
}
