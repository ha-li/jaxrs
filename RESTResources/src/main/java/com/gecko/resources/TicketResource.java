package com.gecko.resources;

import com.gecko.domain.json.Message;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 05/18/17.
 */
@Path ("ticket")
public class TicketResource {


   @GET
   @Produces ({MediaType.APPLICATION_JSON})
   @Path("{id}")
   public Response getTicket(@PathParam("id") String id) {
      Message message = new Message();
      message.setMsg("Hello World! Welcome to ticket " + id + "!");
      return Response.ok().entity(message).build();
   }

   @POST
   @Produces ({MediaType.APPLICATION_JSON})
   @Path("{id}")
   public Response createTicket(Message message) {

      Message resMessage = new Message();
      resMessage.setMsg ("Server hand-shake. " + message.getMsg ());
      //message.setMsg("Hello World! Welcome to ticket " + id + "!");
      return Response.ok().entity(resMessage).build();
   }

   @GET
   @Path("name/{name}")
   public String getTicketId(@PathParam("name") String user) {
      Message message = new Message();
      message.setMsg("Hello " + user + "!");
      return message.getMsg ();
   }

   @GET
   public String getTicketQuery(@QueryParam ("name") @DefaultValue ("World") String user) {
      Message message = new Message();
      message.setMsg("Hello " + user + "!");
      return message.getMsg ();
   }
}
