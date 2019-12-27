package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.service.UserService;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;
import java.util.List;

public class GetAllUsersController implements Controller {
    private UserService userService;

    public GetAllUsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView process(Request request) {
        List<User> users = userService.getAll(request.getUser());
        ModelAndView mv = ModelAndView.of("people-list");
        mv.setData("users", users);
        return mv;
    }
}
