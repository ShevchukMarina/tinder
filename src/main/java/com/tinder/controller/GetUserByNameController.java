package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.service.UserService;
import com.tinder.web.Cookie;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

import java.util.HashMap;
import java.util.Map;

public class GetUserByNameController implements Controller {
    private UserService userService;

    public GetUserByNameController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView process(Request request) {
        Map<String, Object> data = new HashMap<>();
        String name = request.getParam("name");
        User user = userService.getByName(name);
        data.put("user", user);
        data.put("cookie", Cookie.of("APP_KEY", user.getName()));
        return ModelAndView.of("login", data);
    }
}
