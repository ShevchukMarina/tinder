package com.tinder.web.servlet;

        import com.tinder.Factory;
        import com.tinder.ViewBuilder;
        import com.tinder.controller.Controller;
        import com.tinder.model.User;
        import com.tinder.web.ModelAndView;
        import com.tinder.web.Request;

        import javax.servlet.ServletException;
        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.HashMap;
        import java.util.Map;


public class LoginServlet extends HttpServlet {

    private ViewBuilder viewBuilder;
    private Map<Request, Controller> controllerMap = new HashMap<>();
    @Override
    public void init() throws ServletException {
        viewBuilder = Factory.getViewBuilder(Factory.getUsersFreemarkerConfiguration());
        controllerMap.put(Request.of(Request.Method.GET, "/login"), r -> ModelAndView.of("login") );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    String login = req.getParameter("login");
//    String password = req.getParameter("password");
//
//        Object usersDao;
//        User user = usersDao.getUserByLoginAndPassword(login, password);
//
//    if (user == null) {
//        resp.getWriter().write("cookie null");
//    } else {
//        Cookie cookie = new Cookie("user-id", String.valueOf(user.getId()));
//        resp.addCookie(cookie);
//
//    }
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String viewName = "login";

        String view = viewBuilder.buildView(ModelAndView.of(viewName));
        writer.println(view);
    }}

