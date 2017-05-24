package com.gecko.resources;

import com.gecko.domain.json.Subscription;
import com.gecko.rest.headers.GeckoHeaders;

import javax.ws.rs.CookieParam;
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
   public Response getSubscription (
           @HeaderParam (GeckoHeaders.CLIENT_ID) String clientId,
           @PathParam ("id") String userId){

      Subscription subscription = new Subscription ();
      subscription.setUser(userId);
      subscription.setId (clientId);
      return Response.ok().entity(subscription).build();
   }


   /**
    * Make use of the cookie header. To send a cookie header in postman,
    * enable the Interceptor feature, then create a header called Cookie:
    * Cookie: name=value;name2=value2
    *
    * based on the cookie named chocolate the header would be
    * Cookie: sub-id=132846;realm=34223
    * 
    * @param clientId
    * @param userId
    * @return
    */
   @GET
   @Path("websub/{id}")
   public Response withCookie (
           @CookieParam("sub-id") String clientId,
           @CookieParam("realm") String realmId,
           @PathParam ("id") String userId) {

      Subscription subscription = new Subscription ();
      subscription.setUser(userId);
      subscription.setId (clientId);
      return Response.ok().entity(subscription).build();
   }
}
