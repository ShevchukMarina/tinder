package com.tinder.dao;

import com.tinder.model.User;
import java.util.List;

public interface UserDao {
    User insert(User user);
    User getByName(String name);
    User getById(long id);
    List<User> getAll(User user);
}
