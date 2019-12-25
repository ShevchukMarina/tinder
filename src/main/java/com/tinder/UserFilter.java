package com.tinder;

import com.tinder.dao.UserDao;
import com.tinder.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    private UserDao userDao;
    private static final String COOKIE_KEY = "APP_KEY";

    @Override
    public void init(FilterConfig filterConfig) {
        userDao = Factory.getUserDao();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String reqUrl = req.getRequestURI();

        Cookie[] cookies = req.getCookies() == null ? new Cookie[] {} : req.getCookies();
        String name = "";
        User user;

        for (Cookie c : cookies) {
            if (c.getName().equals(COOKIE_KEY)) {
                name = c.getValue();
            }
        }

        if (name.isEmpty()) {
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            user = userDao.getByName(name);

            if (user == null) {
                request.getRequestDispatcher("/login").forward(request, response);
            } else if (reqUrl.equals("/login")) {
                resp.sendRedirect(req.getContextPath() + "/users");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
