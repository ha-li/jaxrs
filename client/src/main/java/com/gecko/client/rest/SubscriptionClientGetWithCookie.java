package com.gecko.client.rest;

import com.gecko.json.bind.JsonUnMarshaller;
import com.gecko.schema.subscription.v1.Subscription;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/5/17.
 */
public class SubscriptionClientGetWithCookie {
   public static void main (String[] args) {
      ClientBuilder jerseyBuilder = new JerseyClientBuilder ();

      Client jserseyClient = jerseyBuilder.build();
      Cookie subIdCookie = new Cookie ("sub-id", "app-id-client");
      Cookie realmCookie = new Cookie ("realm", "hidden-realm");
      WebTarget subscriptionTarget = jserseyClient.target("http://localhost:8080/restAdapter/subscription");
      WebTarget contextSubscriptTarget = subscriptionTarget.path("/websub/{id}")
              .resolveTemplate("id", "Jamica");


      Response subscriptContextResponse = contextSubscriptTarget
              .request(MediaType.APPLICATION_XML)
              .accept(MediaType.APPLICATION_JSON)
              .cookie(subIdCookie)
              .cookie(realmCookie)
              .get();
      Subscription subscription = (Subscription) subscriptContextResponse.readEntity (Subscription.class);

      System.out.println ("id: " + subscription.getId () + ", user: " + subscription.getUser ());

      String output = null;
      try {
         output = JsonUnMarshaller.formatForOutput (subscription);
      } catch (Exception e) {
         e.printStackTrace ();
      }

      System.out.println (output);
   }
}
