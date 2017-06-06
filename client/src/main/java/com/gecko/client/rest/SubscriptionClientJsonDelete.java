package com.gecko.client.rest;

import com.gecko.json.bind.JsonUnMarshaller;
import com.gecko.schema.subscription.v1.Subscription;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/5/17.
 */
public class SubscriptionClientJsonDelete {
   public static void main (String[] args) {
      ClientBuilder jerseyBuilder = new JerseyClientBuilder ();

      Client jserseyClient = jerseyBuilder.build();
      WebTarget subscriptionTarget = jserseyClient.target("http://localhost:8080/restAdapter/subscription");
      Response jrsyResponse = subscriptionTarget.path("/subscriber/{id}/{user}")
              .resolveTemplate("id", "123")
              .resolveTemplate("user", "teahouseFresca")
              .request()
              .delete(Response.class);

      // Response jrsyResponse = contextSubscriptTarget.request().delete (Response.class);
      Subscription response = (Subscription) jrsyResponse.readEntity (Subscription.class);

      System.out.println ("id: " + response.getId () + ", user: " + response.getUser ());

      String output = null;
      try {
         output = JsonUnMarshaller.formatForOutput (response);
      } catch (Exception e) {
         e.printStackTrace ();
      }

      System.out.println (output);
   }
}
