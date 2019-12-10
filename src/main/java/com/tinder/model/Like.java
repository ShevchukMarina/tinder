package com.tinder.model;

public class Like {
    private User from;
    private User to;

    public Like(User from, User to) {
        this.from = from;
        this.to = to;
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
}
