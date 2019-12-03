package com.tinder;

import com.tinder.web.MainServlet;
import freemarker.template.Configuration;
import freemarker.template.Version;

public class Factory {

    public static Configuration getFreemarkerConfiguration() {
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(MainServlet.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    public static FreemarkerViewBuilder getViewBuilder() {
        return new FreemarkerViewBuilder(getFreemarkerConfiguration());
    }

}
