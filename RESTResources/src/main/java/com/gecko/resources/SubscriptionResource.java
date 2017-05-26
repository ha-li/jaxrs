package com.gecko.resources;

import com.gecko.domain.json.Subscription;
import com.gecko.rest.headers.GeckoHeaders;

import javax.ws.rs.CookieParam;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Created by hlieu on 05/23/17.
 */
@Path ("subscription")
@Produces({MediaType.APPLICATION_JSON})
public class SubscriptionResource {

   @Context
   private UriInfo uriInfo;

   @Context
   private Application application;

   @Context
   private Request request;

   @Context
   private SecurityContext securityContext;

   // other context you can inject include
   // Providers (like MessageBodyReader, MessageBodyWriter, ExceptionMapper, ContextResolver
   // HttpServletRequest
   // HttpServletResponse
   // HttpServletContext
   // ServletConfig

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

   @GET
   @Path("context")
   public Response withContext (
           @Context HttpHeaders headers
   ) {
      Subscription subscription = new Subscription ();
      subscription.setUser(headers.getHeaderString (GeckoHeaders.CLIENT_ID));

      MultivaluedMap<String, String> requestHeadermap = headers.getRequestHeaders ();
      for(String headerKey : requestHeadermap.keySet ()) {
         System.out.println ("header: " + headerKey + ", value: " + requestHeadermap.get(headerKey));
      }

      // security Context gets injected by the rest engine (Jersey) in this case.
      // and is a basic interface for a few security related information.
      System.out.println (securityContext.getUserPrincipal () + " was authenticated by " + securityContext.getAuthenticationScheme () );

      return Response.ok().entity(subscription).build();
   }

   /*
    * An example of @Encoded annotation. To see this working:
    *  1. remove the @Encoded and submit a request to this URI with
    *      .../encoded/John Blight and see what @param name is initialized as.
    *  2. repeat but this time add back in @Encoded
    *
    *  Now do the same with %20 as white space, eg John%20Blight as the name.
    *
    *  @Encoded can be a class level annotation, or a method or a parameter.
    *  By default JAX-RS decodes all parameters before injecting the values
    *  into the target variables. With the annotation set, JAX-RS will disable
    *  the decoding, and inject the encoded value, as demonstrated by the
    *  example above.
    *
    */

   @GET
   @Path("encoded/{name}")
   public Response encoded (
      @Encoded
      @PathParam("name") String name
   ) {
      Subscription subscription = new Subscription ();
      subscription.setUser(name);

      return Response.ok().entity(subscription).build();
   }
}
