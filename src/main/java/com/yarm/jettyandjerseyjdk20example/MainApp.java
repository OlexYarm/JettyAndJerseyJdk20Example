package com.yarm.jettyandjerseyjdk20example;

import java.util.TimeZone;
import org.eclipse.jetty.server.CustomRequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.RequestLogWriter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class MainApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(UserResources.class);

        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/api/v1/*");

        DefaultHandler defaultHandler = new DefaultHandler();
        defaultHandler.setShowContexts(false);

        HandlerList handlerLists = new HandlerList();

        handlerLists.setHandlers(new Handler[]{handler, defaultHandler});

        Server server = new Server(8080);
        server.setHandler(handlerLists);

        // https://eclipse.dev/jetty/documentation/jetty-11/programming-guide/index.html#pg-server
        // Use a file name with the pattern 'yyyy_MM_dd' so rolled over files retain their date.
        RequestLogWriter logWriter = new RequestLogWriter("../../Jetty.Requests.yyyy_MM_dd.log");
        // Retain rolled over files for 2 weeks.
        logWriter.setRetainDays(14);
        // Log times are in the current time zone.
        logWriter.setTimeZone(TimeZone.getDefault().getID());
        // Set the RequestLog to log to the given file, rolling over at midnight.
        CustomRequestLog customRequestLog = new CustomRequestLog(logWriter, CustomRequestLog.EXTENDED_NCSA_FORMAT);
        server.setRequestLog(customRequestLog);

        LOGGER.info("#### Starting Jetty server.");
        try {
            server.start();
        } catch (Exception ex) {
            LOGGER.error("#### Cannot start Jetty server. Exception=" + ex.toString());
        }
        LOGGER.info("#### Started Jetty server.");
        try {
            server.join();
        } catch (InterruptedException ex) {
            LOGGER.error("#### Cannot join Jetty server. Exception=" + ex.toString());
        }
        LOGGER.info("#### Stopped Jetty server.");
        System.out.println("#### Stopped Jetty server.");
    }

}
