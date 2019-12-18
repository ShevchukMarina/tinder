package com.tinder.service;

import com.tinder.model.User;
import java.util.List;

public interface UserService {
    User getByName(String name);
    List<User> getAll(User user);
}
