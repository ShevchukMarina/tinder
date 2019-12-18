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
    public Like update(Like like, boolean delete) {
        if (likeDao.isPresent(like)) {
            if (delete) {
                return likeDao.delete(like);
            } else {
                return like;
            }
        } else {
            if (delete) {
                return like;
            } else {
                return likeDao.insert(like);
            }
        }
    }

    @Override
    public List<User> getUsers(User user) {
        return likeDao.getUsers(user);
    }
}
