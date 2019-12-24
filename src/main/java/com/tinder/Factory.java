package com.tinder;

import com.tinder.controller.Controller;
import com.tinder.controller.GetAllUsersController;
import com.tinder.controller.GetUserByNameController;
import com.tinder.dao.*;
import com.tinder.service.*;

import com.tinder.web.servlet.LikedServlet;
import com.tinder.web.servlet.LoginServlet;
import com.tinder.web.servlet.MessagesServlet;
import com.tinder.web.servlet.UsersServlet;
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

    public static Controller getGetAllUsersController() {
        return new GetAllUsersController(getUserService());
    }

    public static Controller getGetUserByNameController() {
        return new GetUserByNameController(getUserService());
    }
}
