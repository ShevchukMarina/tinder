package com.tinder;

import com.tinder.controller.Controller;
import com.tinder.controller.GetAllUsersController;
import com.tinder.controller.GetUserByNameController;
import com.tinder.dao.*;
import com.tinder.service.*;

import com.tinder.web.servlet.LikedServlet;
import com.tinder.web.servlet.LoginServlet;
import com.tinder.web.servlet.MassagesServlet;
import com.tinder.web.servlet.UsersServlet;
import freemarker.template.Configuration;
import freemarker.template.Version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    public static Configuration getUsersFreemarkerConfiguration() {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassLoaderForTemplateLoading(UsersServlet.class.getClassLoader(), "templates");
        cfg.setClassForTemplateLoading(UsersServlet.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    public static Configuration getLikedFreemarkerConfiguration() {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassLoaderForTemplateLoading(LikedServlet.class.getClassLoader(), "templates");
        cfg.setClassForTemplateLoading(LikedServlet.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    public static Configuration getMassagesFreemarkerConfiguration() {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassLoaderForTemplateLoading(MassagesServlet.class.getClassLoader(), "templates");
        cfg.setClassForTemplateLoading(MassagesServlet.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    public static Configuration getLoginFreemarkerConfiguration() {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassLoaderForTemplateLoading(LoginServlet.class.getClassLoader(), "templates");
        cfg.setClassForTemplateLoading(LoginServlet.class, "/");
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
