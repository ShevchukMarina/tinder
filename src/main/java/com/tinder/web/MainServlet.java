package com.tinder.web;

import com.tinder.Factory;
import com.tinder.ViewBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    private ViewBuilder viewBuilder;

    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String viewName = "login";
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "Freemarker");

        String view = viewBuilder.buildView(ModelAndView.of(viewName));

        writer.println(view);
    }

}
