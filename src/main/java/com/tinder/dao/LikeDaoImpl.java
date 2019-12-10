package com.tinder.dao;

import com.tinder.model.Like;
import com.tinder.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Like getByUsers(User from, User to) {
        String query = "SELECT F.ID, F.NAME, F.PHOTO, T.ID, T.NAME, T.PHOTO FROM LIKES L\n" +
                "JOIN USERS F ON L.FROM=F.ID\n" +
                "JOIN USERS T ON L.TO=T.ID\n" +
                "WHERE F.ID=? AND T.ID=?";
        Like result = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, from.getId());
            statement.setLong(2, to.getId());
            rs = statement.executeQuery();
            result = toLike(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Like toLike(ResultSet rs) throws SQLException {
        Like result = null;

        if (rs.next()) {
            User from = new User(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3)
            );

            User to = new User(
                    rs.getLong(4),
                    rs.getString(5),
                    rs.getString(6)
            );

            result = new Like(from, to);
        }

        return result;
    }
}
