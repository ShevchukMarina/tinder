package com.tinder.web.servlet;

import com.tinder.Factory;
import com.tinder.ViewBuilder;
import com.tinder.controller.Controller;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyCookie;
import com.tinder.web.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    private ViewBuilder viewBuilder;
    private Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder(Factory.getFreemarkerConfiguration(UsersServlet.class));
        controllerMap.put(Request.of(Request.Method.GET, "/users"), Factory.getGetAllUsersController());
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
        Request request = Request.of(req.getMethod(), req.getRequestURI(), req.getParameterMap(), MyCookie.getUser(req));
        Controller controller = controllerMap.getOrDefault(request, r -> ModelAndView.of("404"));
        ModelAndView mv = controller.process(request);
        String view = viewBuilder.buildView(mv);
        writer.println(view);
    }

}
