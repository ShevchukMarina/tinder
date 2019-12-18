package com.tinder.controller;

import com.tinder.service.UserService;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

public class GetUserByNameController implements Controller {
    private UserService userService;

    public GetUserByNameController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView process(Request request) {
        return null;
    }
}
