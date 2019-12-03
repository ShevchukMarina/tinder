package com.tinder.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class EmbeddedServer {
    public static void main(String[] args) throws Exception {

        Server server = new Server(7070);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(MainServlet.class, "/");
        server.start();
    }
}
