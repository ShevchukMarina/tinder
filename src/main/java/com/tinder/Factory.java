package com.tinder;

import com.tinder.web.servlet.LikedServlet;
import com.tinder.web.servlet.LoginServlet;
import com.tinder.web.servlet.MassagesServlet;
import com.tinder.web.servlet.UsersServlet;
import freemarker.template.Configuration;
import freemarker.template.Version;

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

}
