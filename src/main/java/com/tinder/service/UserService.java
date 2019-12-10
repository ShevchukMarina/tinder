package com.tinder.service;

import com.tinder.model.User;

import java.util.List;

public interface UserService {
    User add(User user);
    User getByName(String name);
    List<User> getAll();
}
