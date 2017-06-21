package com.gecko.client.rest;

import com.gecko.config.CustomJacksonJsonProvider;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Created by hlieu on 06/18/17.
 */
public class SubscriptionClient {

   // private static ClientBuilder;
   private static Client subscriptionClient = null;
   private static final String RESOURCE_TARGET = "http://localhost:8080/restAdapter/subscription";
   private static WebTarget BASE_REST_TARGET = null;
   static {
      subscriptionClient = new JerseyClientBuilder ()
              .build();
      BASE_REST_TARGET = subscriptionClient.target(RESOURCE_TARGET);
   }

   public WebTarget resourcePath (String path) {
      WebTarget target = BASE_REST_TARGET.path (path);
      return target;
   }
}
