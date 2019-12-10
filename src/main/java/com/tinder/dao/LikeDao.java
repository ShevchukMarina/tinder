package com.tinder.dao;

import com.tinder.model.Like;
import com.tinder.model.User;
import java.util.List;

public interface LikeDao {
    Like add(Like like);
    List<User> getUsers(User user);
}
