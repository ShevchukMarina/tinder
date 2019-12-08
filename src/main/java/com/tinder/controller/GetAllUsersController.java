package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsersController implements Controller {

    @Override
    public ModelAndView process(Request request) {
        Map<String, Object> data = new HashMap<>();
        List<User> users = new ArrayList<>();
        data.put("users", users);
        return ModelAndView.of("people-list", data);
    }
}
