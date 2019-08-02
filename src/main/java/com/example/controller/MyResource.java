package com.example.controller;

import com.example.model.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Root resource
 */
@Path("")
public class MyResource {
    private static final String PLAIN_TEXT_ENCODING = "UTF-8";

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt() {
        return Response.ok()
                .entity("Got it!")
                .encoding(PLAIN_TEXT_ENCODING).build();
    }

    @GET
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() {
        Person p = new Person();
        p.setFirstName("Leon");
        LocalDate birthDay = LocalDate.of(1989, 1, 1);
        ZonedDateTime birthDayZdt = birthDay.atStartOfDay(ZoneId.of("UTC"));
        Date birthDayDate = Date.from(birthDayZdt.toInstant());
        p.setDateOfBirth(birthDayDate);

        return Response.ok().entity(p).build();
    }
}
