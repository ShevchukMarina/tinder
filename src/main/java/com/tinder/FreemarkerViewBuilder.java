package com.tinder;

import com.tinder.web.ModelAndView;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;

public class FreemarkerViewBuilder implements ViewBuilder {

    private Configuration configuration;

    public FreemarkerViewBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String buildView(ModelAndView vm) {
        String result = null;

        Template template = getTemplate(vm.getViewName());

        try (StringWriter out = new StringWriter()) {

            template.process(vm.getAllData(), out);

            result = out.getBuffer().toString();

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private Template getTemplate(String viewName) {
        Template template = null;

        try {
            template = configuration.getTemplate(String.format("templates/%s.ftl", viewName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return template;
    }
}
