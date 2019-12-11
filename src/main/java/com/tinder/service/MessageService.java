package com.tinder.service;

import com.tinder.model.Message;
import com.tinder.model.User;

import java.util.List;

public interface MessageService {
    Message add(Message message);
    List<Message> getByUsers(User from, User to);
}
