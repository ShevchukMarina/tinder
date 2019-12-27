package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.service.UserService;
import com.tinder.web.MyCookie;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

public class GetUserByNameController implements Controller {
    private UserService userService;

    public GetUserByNameController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ModelAndView process(Request request) {
        String name = request.getParam("name");
        User user = userService.getByName(name);
        ModelAndView mv = ModelAndView.of("login");
        mv.setData("user", user);
        mv.setData("cookie", MyCookie.of(MyCookie.USER_NAME, user.getName()));
        return mv;
    }
}
