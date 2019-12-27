package com.tinder.web.servlet;

import com.tinder.Factory;
import com.tinder.ViewBuilder;
import com.tinder.controller.Controller;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LikedServlet extends HttpServlet {
    private ViewBuilder viewBuilder;
    private Map<MyRequest, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder(Factory.getFreemarkerConfiguration(LikedServlet.class));
        controllerMap.put(MyRequest.of(MyRequest.Method.GET, "/liked"), r -> ModelAndView.of("like-page") );
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

        String viewName = "like-page";

        String view = viewBuilder.buildView(ModelAndView.of(viewName));
        writer.println(view);
    }
}
