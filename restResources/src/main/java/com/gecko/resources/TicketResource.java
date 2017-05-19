package com.gecko.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by hlieu on 05/18/17.
 */
@Path ("/ticket")
public class TicketResource {
   @GET
   @Path("/{id}")
   public String getTicket(@PathParam("id") String id) {
      return "Hello World " + id;
   }
}
