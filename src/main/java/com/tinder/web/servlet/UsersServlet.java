package com.tinder.web.servlet;

import com.tinder.Factory;
import com.tinder.ViewBuilder;
import com.tinder.controller.Controller;
import com.tinder.model.User;
import com.tinder.web.ModelAndView;
import com.tinder.web.MyCookie;
import com.tinder.web.MyRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    private ViewBuilder viewBuilder;
    private Map<MyRequest, Controller> controllerMap = new HashMap<>();
    private ModelAndView mv;

    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder(Factory.getFreemarkerConfiguration(UsersServlet.class));
        controllerMap.put(MyRequest.of(MyRequest.Method.GET, "/users"), Factory.getGetAllUsersController());
        controllerMap.put(MyRequest.of(MyRequest.Method.POST, "/users"), Factory.getGetAllUsersController());
        mv = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (mv != null) {
            List<User> users = (List<User>) mv.getData("users");
            users.remove(0);

            if (users.size() == 0) {
                mv = null;
            }
        }
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        MyRequest myRequest = MyRequest.of(req.getMethod(), req.getRequestURI(), req.getParameterMap(), MyCookie.getUser(req));
        Controller controller = controllerMap.getOrDefault(myRequest, r -> ModelAndView.of("404"));
        if(mv == null) {
            mv = controller.process(myRequest);
        }
        String view = viewBuilder.buildView(mv);
        writer.println(view);
    }

}
