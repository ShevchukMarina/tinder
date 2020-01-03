package com.tinder.dao;

import com.tinder.model.Message;
import com.tinder.model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MessageDaoImpl implements MessageDao {
    private Connection connection;

    public MessageDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Message insert(Message message) {
        String query = "INSERT INTO MESSAGES(\"from\", \"to\", body) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, message.getFrom().getId());
            statement.setLong(2, message.getTo().getId());
            statement.setString(3, message.getBody());
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
    public List<Message> getByUsers(User from, User to) {
        String query = "SELECT F.ID, F.NAME, T.ID, T.NAME, M.ID, M.BODY FROM MESSAGES M\n" +
                "JOIN USERS F ON M.FROM=F.ID\n" +
                "JOIN USERS T ON M.TO=T.ID\n" +
                "WHERE M.FROM=? AND M.TO=? OR M.FROM=? AND M.TO=?";
        List<Message> result = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, from.getId());
            statement.setLong(2, to.getId());
            statement.setLong(3, to.getId());
            statement.setLong(4, from.getId());
            rs = statement.executeQuery();
            result = toMessages(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<Message> toMessages(ResultSet rs) throws SQLException {
        List<Message> result = new ArrayList<>();
        while(rs.next()) {
            User from = new User(
                    rs.getLong(1),
                    rs.getString(2)
            );

            User to = new User(
                    rs.getLong(3),
                    rs.getString(4)
            );

            result.add(new Message(
                    rs.getLong(5),
                    from,
                    to,
                    rs.getString(6)
            ));
        }
        return result;
    }
}
