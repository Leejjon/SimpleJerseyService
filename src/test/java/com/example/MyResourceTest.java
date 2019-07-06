package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer("localhost", "8080");

        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target("http://localhost:8080/");
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    /**
     * Test to see that the person with firstname "Leon" and date of birth "1989-01-01" is returned in proper json.
     */
    @Test
    public void testGetPerson() {
        String responseMsg = target.path("person").request().get(String.class);
        assertEquals("{\"dateOfBirth\":\"1989-01-01T00:00:00Z[UTC]\",\"firstName\":\"Leon\"}", responseMsg);
    }
}
