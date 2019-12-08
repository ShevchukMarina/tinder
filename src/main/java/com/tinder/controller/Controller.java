package com.tinder.controller;

import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

public interface Controller {

    ModelAndView process(Request request);
}
