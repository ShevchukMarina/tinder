package com.tinder.controller;

import com.tinder.Factory;
import com.tinder.model.Message;
import com.tinder.model.User;
import com.tinder.service.MessageService;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;

import java.util.List;

public class AddMessageController implements Controller {
    private MessageService messageService;

    public AddMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public ModelAndView process(MyRequest myRequest) {
        User user = Factory.getUserDao().getById(Long.parseLong(myRequest.getParam("id")));
        Message message = new Message(0, myRequest.getUser(), user, myRequest.getParam("message"));
        messageService.add(message);

        List<Message> messages = messageService.getByUsers(myRequest.getUser(), user);
        ModelAndView mv = ModelAndView.of("chat");
        mv.setData("user", user);
        mv.setData("messages", messages);
        return mv;
    }
}
