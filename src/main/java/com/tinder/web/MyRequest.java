package com.tinder.web;

import com.tinder.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyRequest {

    private Method method;
    private String uri;
    private Map<String, String[]> params;
    private User user;

    public MyRequest(Method method, String uri, Map<String, String[]> params, User user) {
        this.method = method;
        this.uri = uri;
        this.params = new HashMap<>();
        this.user = user;

        if(params != null) {
            this.params.putAll(params);
        }
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

    public void setParam(String key, String value) {
        String[] values = {value};
        params.put(key, values);
    }

    public static MyRequest of(String method, String uri, Map<String, String[]> params, User user) {
        return new MyRequest(Method.valueOf(method), uri, params, user);
    }

    public static MyRequest of(String method, String uri, Map<String, String[]> params) {
        return new MyRequest(Method.valueOf(method), uri, params, null);
    }

    public static MyRequest of(Method method, String uri){
        return new MyRequest(method, uri, null, null);
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
        MyRequest myRequest = (MyRequest) o;
        return method == myRequest.method &&
                Objects.equals(uri, myRequest.uri);
    }
}
