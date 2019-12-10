package com.tinder.dao;

import com.tinder.model.Like;
import com.tinder.model.User;
import java.util.List;

import java.sql.*;

public class LikeDaoImpl implements LikeDao{
    private Connection connection;

    public LikeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Like add(Like like) {
        String query = "INSERT INTO LIKES VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, like.getFrom().getId());
            statement.setLong(2, like.getTo().getId());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return like;
    }

    @Override
    public List<User> getUsers(User user) {
        String query = "SELECT T.ID, T.NAME, T.PHOTO FROM LIKES L\n" +
                "JOIN USERS F ON L.FROM=F.ID\n" +
                "JOIN USERS T ON L.TO=T.ID\n" +
                "WHERE F.ID=?";
        List<User> result = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            rs = statement.executeQuery();
            result = UserDaoImpl.toUsers(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
