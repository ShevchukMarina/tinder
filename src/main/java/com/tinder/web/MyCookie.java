package com.tinder.web;

import com.tinder.Factory;
import com.tinder.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class MyCookie {
    public static final String USER_NAME = "USER_NAME";
    private String name;
    private String value;

    private MyCookie(String name, String value) {
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

    public static MyCookie of(String name, String value) {
        return new MyCookie(name, value);
    }

    public static User getUser(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() == null ? new Cookie[] {} : req.getCookies();
        String name = "";

        for (Cookie c : cookies) {
            if (c.getName().equals(USER_NAME)) {
                name = c.getValue();
            }
        }

        if (name.isEmpty()) {
            return null;
        } else {
            return Factory.getUserDao().getByName(name);
        }
    }
}
