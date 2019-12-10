package com.tinder.service;

import com.tinder.dao.LikeDao;
import com.tinder.model.Like;
import com.tinder.model.User;

import java.util.List;

public class LikeServiceImpl implements LikeService {
    private LikeDao likeDao;

    public LikeServiceImpl(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    @Override
    public Like add(Like like) {
        return likeDao.add(like);
    }

    @Override
    public List<User> getUsers(User user) {
        return likeDao.getUsers(user);
    }
}
