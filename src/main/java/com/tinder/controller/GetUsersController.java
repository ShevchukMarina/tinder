package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.service.UserService;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;
import java.util.List;

public class GetUsersController implements Controller {
    private UserService userService;

    public GetUsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView process(MyRequest myRequest) {
        List<User> users = userService.getAll(myRequest.getUser());
        ModelAndView mv = ModelAndView.of("people-list");
        mv.setData("users", users);
        return mv;
    }
}
