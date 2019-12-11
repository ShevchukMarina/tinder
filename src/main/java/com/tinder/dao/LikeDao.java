package com.tinder.dao;

import com.tinder.model.Like;
import com.tinder.model.User;
import java.util.List;

public interface LikeDao {
    boolean isPresent(Like like);
    Like insert(Like like);
    Like delete(Like like);
    List<User> getUsers(User user);
}
