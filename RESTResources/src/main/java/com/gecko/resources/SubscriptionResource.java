package com.gecko.resources;

import com.gecko.rest.headers.GeckoHeaders;
import com.gecko.schema.subscription.v1.Subscription;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

/**
 * Created by hlieu on 05/23/17.
 */

/*
 *  Root resource class - any pojo class that has @Path or has subroutines with @Path in it.
 *  By default, every time a request is made, a new instance of the root resource class is created.
 *  The scope of the root resource class is limited to that request only.
 *
 *  By limiting the scope of the root resource class to that request only, multiple concurrent requests
 *  to the root resource are easily managed. The application developer does not need to do anything
 *  to handle multiple concurrent requests.
 *
 *  If the root resource was a singleton, there would be performance issues when handling multiple
 *  concurrent requests.
 *
 *  With request scoped resource classes, the JVM can easily do garbage collection once the instance
 *  has handled the request.
 *
 *  We can also make a root resource a singleton by adding @Singleton annotation at the class level.
 */

// @Singleton - to make this root resource a singleton, uncomment this annotation
@Path ("subscription")
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class SubscriptionResource {

   @Context
   private UriInfo uriInfo;

   @Context
   private Application application;

   @Context
   private Request request;

   @Context
   private SecurityContext securityContext;

   // this shows you can use query params not just at a method parameter level,
   // but also as a class level. As long as you do not make the root resource a singleton,
   // this is not an issue.
   @QueryParam("name")
   private String name;

   private String bobIsName;

   public SubscriptionResource (@QueryParam ("name") String queryName) {
      bobIsName = queryName;
   }

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
      System.out.println (this);
      Subscription subscription = new Subscription ();
      subscription.setUser(name);
      CacheControl cacheControl = new CacheControl ();
      cacheControl.setMaxAge (86400);   // 1 day in secs
      return Response.ok().cacheControl (cacheControl).entity(subscription).build();
   }

   // the request should specify header "Content-Type: application/json"
   @POST
   @Path("subscriber/{id}")
   public Response createSubscription (@PathParam("id") String id, Subscription request) {
      System.out.println ("creating a subscription");
      Subscription subscription = new Subscription ();
      subscription.setUser(id);
      subscription.setId(UUID.randomUUID ().toString());
      CacheControl cacheControl = new CacheControl ();
      cacheControl.setMaxAge (86400);   // 1 day in secs
      return Response.ok().cacheControl (cacheControl).entity(subscription).build();
   }

   @PUT
   @Path ("subscriber/{id}")
   public Response updateSubscription (@PathParam("id") String id, Subscription update) {
      System.out.println ("updating a subscription");
      Response created = createSubscription(id, update);
      Subscription createdSub = (Subscription) created.getEntity();
      createdSub.setId(id);
      createdSub.setUser(update.getUser());
      return Response.ok().entity(createdSub).build();
   }

   @DELETE
   @Path("subscriber/{id}/{user}")
   public Response deleteSubscription (@PathParam("id") String id, @PathParam("user") String user) {

      String deleteId = id;
      String deleteUser = user;

      Subscription deleted = new Subscription ();
      if (true) {
         deleted.setId (id);
         deleted.setUser(deleteUser);
         return Response.ok ().entity (deleted).build ();
      }
      return Response.status (Response.Status.CONFLICT).entity(deleted).build();
   }
}
