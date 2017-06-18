package com.gecko.resources;

import com.gecko.schema.subscription.v1.Message;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
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
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TicketResource {


   @GET
   @Path("{id}")
   public Response getTicket(@PathParam("id") String id) {
      Message message = new Message ();
      message.setMsg("Hello World! Welcome to ticket " + id + "!");
      return Response.ok().entity(message).build();
   }

   @GET
   @Path("{user}/{ticketid}")
   public Response getTicket(@PathParam("user") String user, @PathParam("ticketid") String ticketid) {
      Message message = new Message();
      message.setMsg("Hello " + user +", you are the owner of ticket " + ticketid + "!");
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

   @GET
   @Path("dept")
   public Response purchaseTicket (@MatrixParam ("user") String user, @MatrixParam ("dept") String department) {
      Message resMessage = new Message();

      resMessage.setMsg ("Department ticket purchase: User=" + user + ", Dept=" + department );
      //message.setMsg("Hello World! Welcome to ticket " + id + "!");
      return Response.ok().entity(resMessage).build();
   }
}
