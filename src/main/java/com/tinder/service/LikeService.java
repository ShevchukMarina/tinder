package com.tinder.service;

import com.tinder.model.Like;
import com.tinder.model.User;
import java.util.List;

public interface LikeService {
    Like update(Like like, boolean delete);
    List<User> getUsers(User user);
}
