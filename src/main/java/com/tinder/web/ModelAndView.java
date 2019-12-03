package com.tinder.web;

import java.util.Map;

public class ModelAndView {

    private String viewName;
    private Map<String, Object> data;

    public ModelAndView(String viewName, Map<String, Object> data) {
        this.viewName = viewName;
        this.data = data;
    }

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static ModelAndView of(String viewName) {
        return new ModelAndView(viewName);
    }

    public static ModelAndView of (String viewName, Map<String, Object> data) {
        return new ModelAndView(viewName, data);
    }
}
