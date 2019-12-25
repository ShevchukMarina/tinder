package com.tinder.web;

public class Cookie {

    private String name;
    private String value;

    private Cookie(String name, String value) {
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

    public static Cookie of(String name, String value) {
        return new Cookie(name, value);
    }
}