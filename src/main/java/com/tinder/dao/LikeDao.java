package com.tinder.dao;

import com.tinder.model.Like;
import com.tinder.model.User;

public interface LikeDao {
    Like add(Like like);
    Like getByUsers(User from, User to);
}
