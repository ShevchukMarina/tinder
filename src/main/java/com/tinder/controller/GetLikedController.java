package com.tinder.controller;

import com.tinder.model.User;
import com.tinder.service.LikeService;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;

import java.util.List;

public class GetLikedController implements Controller {
    private LikeService likeService;

    public GetLikedController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Override
    public ModelAndView process(MyRequest myRequest) {
        List<User> users = likeService.getUsers(myRequest.getUser());
        ModelAndView mv = ModelAndView.of("like-page");
        mv.setData("users", users);
        return mv;
    }
}
