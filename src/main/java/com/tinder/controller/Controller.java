package com.tinder.controller;

import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;

public interface Controller {

    ModelAndView process(MyRequest myRequest);
}
