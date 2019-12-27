package com.tinder.web;

import com.tinder.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Request {

    private Method method;
    private String uri;
    private Map<String, String[]> params = new HashMap<>();
    private User user;

    public Request(Method method, String uri, Map<String, String[]> params, User user) {
        this.method = method;
        this.uri = uri;
        this.params = params;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String[]> getParams() {
        return params;
    }

    public void setParams(Map<String, String[]> params) {
        this.params = params;
    }

    public String getParam(String key) {
        return params.get(key)[0];
    }

    public static Request of(String method, String uri, Map<String, String[]> params, User user) {
        return new Request(Method.valueOf(method), uri, params, user);
    }

    public static Request of(String method, String uri, Map<String, String[]> params) {
        return new Request(Method.valueOf(method), uri, params, null);
    }

    public static Request of(Method method, String uri){
        return new Request(method, uri, null, null);
    }

    public enum Method {
        GET,
        POST
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, uri);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return method == request.method &&
                Objects.equals(uri, request.uri);
    }
}
