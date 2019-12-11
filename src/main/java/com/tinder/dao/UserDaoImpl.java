package com.tinder.dao;

import com.tinder.model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static java.sql.Types.BIGINT;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User add(User user) {
        String query = "INSERT INTO USERS VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setNull(1, BIGINT);
            statement.setString(2, user.getName());
            statement.setString(3, user.getPhoto());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getLong(1));
            } else {
                throw new RuntimeException("Error during User creation with name: " + user.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return user;
    }

    @Override
    public User getByName(String name) {
        String query = "SELECT ID, NAME, PHOTO FROM USERS WHERE NAME = ?";
        User result = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            rs = statement.executeQuery();
            result = toUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT ID, NAME, PHOTO FROM USERS";
        List<User> result = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = toUsers(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private User toUser(ResultSet rs) throws SQLException {
        User result = null;

        if (rs.next()) {
            result = new User(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3)
            );
        }

        return result;
    }

    static List<User> toUsers(ResultSet rs) throws SQLException {
        List<User> result = new ArrayList<>();
        while(rs.next()) {
            result.add(new User(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3)));
        }
        return result;
    }
}
