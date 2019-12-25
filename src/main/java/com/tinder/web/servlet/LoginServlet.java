package com.tinder.web.servlet;

import com.tinder.Factory;
import com.tinder.ViewBuilder;
import com.tinder.controller.Controller;
import com.tinder.web.ModelAndView;
import com.tinder.web.Request;
import com.tinder.web.Cookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class LoginServlet extends HttpServlet {

    private ViewBuilder viewBuilder;
    private Map<Request, Controller> controllerMap = new HashMap<>();
    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder(Factory.getFreemarkerConfiguration(LoginServlet.class));
        controllerMap.put(Request.of(Request.Method.GET, "/login"), r -> ModelAndView.of("login"));
        controllerMap.put(Request.of(Request.Method.POST, "/login"), Factory.getGetUserByNameController());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        resp.sendRedirect(req.getContextPath() + "/users");
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        Request request = Request.of(req.getMethod(), req.getRequestURI(), req.getParameterMap());
        Controller controller = controllerMap.getOrDefault(request, r -> ModelAndView.of("404"));
        ModelAndView mv = controller.process(request);
        processCookies(resp, mv.getData());
        String view = viewBuilder.buildView(mv);
        writer.println(view);
    }

    private void processCookies(HttpServletResponse resp, Map<String, Object> data) {
        if (data != null) {
            Optional.ofNullable(data.get("cookie"))
                    .ifPresent(c -> {
                        Cookie cookie = (Cookie) c;
                        resp.addCookie(new javax.servlet.http.Cookie(cookie.getName(), cookie.getValue()));
                    });
        }
    }
}

