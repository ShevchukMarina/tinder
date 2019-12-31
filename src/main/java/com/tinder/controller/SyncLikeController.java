package com.tinder.controller;

import com.tinder.Factory;
import com.tinder.model.Like;
import com.tinder.model.User;
import com.tinder.service.LikeService;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;

public class SyncLikeController implements Controller {
    private LikeService likeService;

    public SyncLikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Override
    public ModelAndView process(MyRequest myRequest) {
        User user = Factory.getUserDao().getByName(myRequest.getParam("user_name"));
        Like like = new Like(myRequest.getUser(), user);
        likeService.update(like, myRequest.getParam("button_name").equals("no"));
        return null;
    }
}
