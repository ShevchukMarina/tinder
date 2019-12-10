package com.tinder.dao;

import com.tinder.model.Message;

public interface MessageDao {
    Message add(Message message);
    Message getById(long id);
}
