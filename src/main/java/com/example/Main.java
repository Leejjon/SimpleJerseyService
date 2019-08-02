package com.example;

import com.example.controller.MyResource;
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
    private static final String PRODUCTION_HOST = "0.0.0.0";
    private static final String PRODUCTION_PORT = System.getenv().get("PORT");
    private static final String LOCAL_HOST = "localhost";
    private static final String LOCAL_PORT = "8080";

    // Base URI the Grizzly HTTP server will listen on
    private static final String BASE_URI = "http://%1$s:%2$s/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @param developing If true, we will add CORS headers with each request to make it easier to run locallys.
     * @return Grizzly HTTP server.
     */
    static HttpServer startServer(boolean developing) {
        // create a resource config that registers the MyResource JAX-RS resource
        final ResourceConfig rc = new ResourceConfig();

        // Registering like this will give warnings like:
        // WARNING: A provider com.example.controller.MyResource registered in SERVER runtime does not implement any provider interfaces applicable in the SERVER runtime. Due to constraint configuration problems the provider
        // com.example.controller.MyResource will be ignored.
        // But it just works and according to stackoverflow this is a bug:
        // https://github.com/jersey/jersey/issues/3700
        rc.register(new MyResource());

        // Disable wadl because I never asked for this.
        rc.property("jersey.config.server.wadl.disableWadl", true);

        final String base = developing ? LOCAL_HOST : PRODUCTION_HOST;
        final String port = developing ? LOCAL_PORT : PRODUCTION_PORT;

        System.out.println(String.format("Jersey app started at %s", String.format(BASE_URI, base, port)));

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(String.format(BASE_URI, base, port)), rc);
    }

    /**
     * Main method.
     * @param args First value should contain "DEV" to start with CORS disabled.
     */
    public static void main(String[] args) {
        boolean developing = false;
        if (args.length > 0 && args[0].contains("LOCAL")) {
            developing = true;
        }

//        enableAllLogging();

        startServer(developing);
    }

    /**
     * Makes sure everything logged by every dependency is coming up.
     */
    private static void enableAllLogging() {
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(Level.ALL);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.ALL);
        }
    }
}

