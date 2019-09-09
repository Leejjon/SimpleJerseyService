package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    private static final String BASE_URI = "http://%1$s:%2$s/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    static HttpServer startServer(String base, String port) {
        // create a resource config that registers the MyResource JAX-RS resource
        final ResourceConfig rc = new ResourceConfig();

        // Registering like this will give warnings like:
        // WARNING: A provider com.example.MyResource registered in SERVER runtime does not implement any provider interfaces applicable in the SERVER runtime. Due to constraint configuration problems the provider
        // com.example.MyResource will be ignored.
        // But it just works and according to stackoverflow this is a bug:
        // https://github.com/jersey/jersey/issues/3700
        rc.register(new MyResource());

        // Disable wadl here if you like.
        rc.property("jersey.config.server.wadl.disableWadl", false);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(String.format(BASE_URI, base, port)), rc);
    }

    /**
     * Main method.
     * @param args Should contain "localhost" or "0.0.0.0" as first argument, and a valid port as second.
     */
    public static void main(String[] args) {
        enableLogging();

        String base = args[0];
        String port = args[1];
        startServer(base, port);
        System.out.println(String.format("Jersey app started at %s", String.format(BASE_URI, base, port)));
    }

    private static void enableLogging() {
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(Level.ALL);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.ALL);
        }
    }
}

