package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.service.UserService;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsersController implements Controller {
    private UserService userService;

    public GetAllUsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView process(Request request) {
        Map<String, Object> data = new HashMap<>();
        List<User> users = userService.getAll(null);
        data.put("users", users);
        return ModelAndView.of("people-list", data);
    }
}
