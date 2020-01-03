package com.tinder.controller;

import com.tinder.Factory;
import com.tinder.model.Message;
import com.tinder.model.User;
import com.tinder.service.MessageService;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;

import java.util.List;

public class GetMessagesController implements Controller {
    private MessageService messageService;

    public GetMessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public ModelAndView process(MyRequest myRequest) {
        User user = Factory.getUserDao().getById(Long.parseLong(myRequest.getParam("id")));
        List<Message> messages = messageService.getByUsers(myRequest.getUser(), user);
        ModelAndView mv = ModelAndView.of("chat");
        mv.setData("user", user);
        mv.setData("messages", messages);
        return mv;
    }
}
