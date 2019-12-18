package com.tinder.web.servlet;

import com.tinder.Factory;
import com.tinder.ViewBuilder;
import com.tinder.controller.Controller;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MassagesServlet extends HttpServlet {

    private ViewBuilder viewBuilder;
    private Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder(Factory.getUsersFreemarkerConfiguration());
        controllerMap.put(Request.of(Request.Method.GET, "/chat"), r -> ModelAndView.of("chat") );
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

        String viewName = "chat";

        String view = viewBuilder.buildView(ModelAndView.of(viewName));
        writer.println(view);
    }}