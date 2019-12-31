package com.tinder.web;

import com.tinder.UserFilter;
import com.tinder.web.servlet.LikedServlet;
import com.tinder.web.servlet.LoginServlet;
import com.tinder.web.servlet.MessagesServlet;
import com.tinder.web.servlet.UsersServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class EmbeddedServer {
    public static void main(String[] args) throws Exception {

        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");

        ServletHolder usersHolder = new ServletHolder(new UsersServlet());
        handler.addServlet(usersHolder, "/users");

        ServletHolder likedHolder = new ServletHolder(new LikedServlet());
        handler.addServlet(likedHolder, "/liked");

        ServletHolder massagesHolder = new ServletHolder(new MessagesServlet());
        handler.addServlet(massagesHolder, "/chat");

        ServletHolder loginHolder = new ServletHolder(new LoginServlet());
        handler.addServlet(loginHolder, "/login");

        handler.addFilter(UserFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        ResourceHandler resourceHandler= new ResourceHandler();
        resourceHandler.setResourceBase("templates");
        resourceHandler.setDirectoriesListed(true);
        ContextHandler contextHandler= new ContextHandler("/public");
        contextHandler.setHandler(resourceHandler);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {contextHandler, handler});
        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
