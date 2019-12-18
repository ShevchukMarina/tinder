package com.tinder.service;

import com.tinder.dao.UserDao;
import com.tinder.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByName(String name) {
        User result = userDao.getByName(name);
        if(result == null) {
            result = userDao.insert(new User(0, name));
        }
        return result;
    }

    @Override
    public List<User> getAll(User user) {
        return userDao.getAll(user);
    }
}
