package com.tinder.service;

import com.tinder.dao.MessageDao;
import com.tinder.model.Message;
import com.tinder.model.User;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDao messageDao;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Message add(Message message) {
        return messageDao.add(message);
    }

    @Override
    public List<Message> getByUsers(User from, User to) {
        return messageDao.getByUsers(from, to);
    }
}
