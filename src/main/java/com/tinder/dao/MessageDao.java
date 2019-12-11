package com.tinder.dao;

import com.tinder.model.Message;
import com.tinder.model.User;

import java.util.List;

public interface MessageDao {
    Message insert(Message message);
    List<Message> getByUsers(User from, User to);
}
