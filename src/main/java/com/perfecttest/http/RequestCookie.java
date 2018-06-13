package com.perfecttest.http;

/**
 * Created by Sidelnikov Mikhail on 25.05.15.
 * request cookie
 */
public class RequestCookie {
    private String name;
    private String value;
    private String expires;
    private String path;


    public RequestCookie(String name, String value) {
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

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RequestCookie withExpires(String expires){
        this.setExpires(expires);
        return this;
    }

    public RequestCookie withPath(String path){
        this.setPath(path);
        return this;
    }

    public String toString() {
        return "Name: " + name + "; value: " + value + "; path: " + path;
    }

}
