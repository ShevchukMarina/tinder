package com.tinder.model;

public class User {

    private long id;
    private String name;
    private String photo;
    private boolean liked;

    public User(long id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }
}
