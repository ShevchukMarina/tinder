package com.tinder;

import com.tinder.controller.*;
import com.tinder.dao.*;
import com.tinder.service.*;

import freemarker.template.Configuration;
import freemarker.template.Version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    public static Configuration getFreemarkerConfiguration(Class servletClass) {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassLoaderForTemplateLoading(servletClass.getClassLoader(), "templates");
        cfg.setClassForTemplateLoading(servletClass, "/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    public static FreemarkerViewBuilder getViewBuilder(Configuration configuration) {
        return new FreemarkerViewBuilder(configuration);
    }

    public static Connection getConnection() {
        String url="jdbc:postgresql://procmain.eu:5432/tinder";
        String username = "tinder";
        String password = "fs9";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static LikeDao getLikeDao() {
        return new LikeDaoImpl(getConnection());
    }

    public static MessageDao getMessageDao() {
        return new MessageDaoImpl(getConnection());
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public static LikeService getLikeService() {
        return new LikeServiceImpl(getLikeDao());
    }

    public static MessageService getMessageService() {
        return new MessageServiceImpl(getMessageDao());
    }

    public static UserService getUserService() {
        return new UserServiceImpl(getUserDao());
    }

    public static Controller getGetUsersController() {
        return new GetUsersController(getUserService());
    }

    public static Controller getGetUserByNameController() {
        return new GetUserByNameController(getUserService());
    }

    public static Controller getSyncLikeController() {
        return new SyncLikeController(getLikeService());
    }

    public static Controller getGetLikedController() {
        return new GetLikedController(getLikeService());
    }

    public static Controller getGetMessagesController() {
        return new GetMessagesController(getMessageService());
    }

    public static Controller getAddMessageController() {
        return new AddMessageController(getMessageService());
    }
}
