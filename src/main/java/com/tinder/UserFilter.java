package com.tinder;

import com.tinder.model.User;
import com.tinder.web.MyCookie;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String reqUrl = req.getRequestURI();
        User user = MyCookie.getUser(req);

        if (user == null) {
            request.getRequestDispatcher("/login").forward(request, response);
        } else if (reqUrl.equals("/login")) {
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
