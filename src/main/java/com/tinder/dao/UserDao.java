package com.tinder.dao;

import com.tinder.model.User;

public interface UserDao {
    User add(User user);
    User getByName(String name);
}
