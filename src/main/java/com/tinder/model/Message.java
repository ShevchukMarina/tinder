package com.tinder.model;

public class Message {
    private long id;
    private User from;
    private User to;
    private String body;

    public Message(long id, User from, User to, String body) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
