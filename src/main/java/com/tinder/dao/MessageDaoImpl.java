package com.tinder.dao;

import com.tinder.model.Message;
import com.tinder.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDaoImpl implements MessageDao {
    private Connection connection;

    public MessageDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Message add(Message message) {
        String query = "INSERT INTO MESSAGES VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, message.getId());
            statement.setLong(2, message.getFrom().getId());
            statement.setLong(3, message.getTo().getId());
            statement.setString(4, message.getBody());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();

            if (rs.next()) {
                message.setId(rs.getLong(1));
            } else {
                throw new RuntimeException("Error during Message creation with body: " + message.getBody());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return message;
    }

    @Override
    public Message getById(long id) {
        String query = "SELECT F.ID, F.NAME, F.PHOTO, T.ID, T.NAME, T.PHOTO, M.ID, M.BODY FROM MESSAGES M\n" +
                "JOIN USERS F ON M.FROM=F.ID\n" +
                "JOIN USERS T ON M.TO=T.ID\n" +
                "WHERE M.ID=?";
        Message result = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            rs = statement.executeQuery();
            result = toMessage(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Message toMessage(ResultSet rs) throws SQLException {
        Message result = null;

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

            result = new Message(
                    rs.getLong(7),
                    from,
                    to,
                    rs.getString(8)
            );
        }

        return result;
    }
}
