package com.tinder.web;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private String viewName;
    private Map<String, Object> data;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
        this.data = new HashMap<>();
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getAllData() {
        return data;
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public void setData(String key, Object value) {
        this.data.put(key, value);
    }

    public static ModelAndView of(String viewName) {
        return new ModelAndView(viewName);
    }
}
