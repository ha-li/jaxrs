package com.gecko.resources;

import com.gecko.domain.json.Subscription;
import com.gecko.rest.headers.GeckoHeaders;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 05/23/17.
 */
@Path ("subscription")
@Produces({MediaType.APPLICATION_JSON})
public class SubscriptionResource {

   @GET
   @Path("{id}")
   public Response getSubscription (@HeaderParam (GeckoHeaders.CLIENT_ID) String clientId, @PathParam ("id") String userId) {
      Subscription subscription = new Subscription ();
      subscription.setUser(userId);
      subscription.setId (clientId);
      return Response.ok().entity(subscription).build();
   }
}
