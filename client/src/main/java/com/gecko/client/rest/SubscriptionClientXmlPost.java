package com.gecko.client.rest;

import com.gecko.domain.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/5/17.
 */
public class SubscriptionClientXmlPost {
   public static void main (String[] args) {
      ClientBuilder jerseyBuilder = new JerseyClientBuilder ();

      Client jserseyClient = jerseyBuilder.build();
      WebTarget subscriptionTarget = jserseyClient.target("http://localhost:8080/restAdapter/subscription");
      WebTarget contextSubscriptTarget = subscriptionTarget.path("/subscriber/teahouseFresca");

      Subscription subscription = new Subscription ();
      Response subscriptContextResponse = contextSubscriptTarget.request().post(Entity.xml (subscription));
      Subscription responseStr = (Subscription) subscriptContextResponse.readEntity (Subscription.class);

      System.out.println ("id: " + responseStr.getId () + ", user: " + responseStr.getUser ());

      String output = null;
      try {
         output = JsonUnMarshaller.formatForOutput (responseStr);
      } catch (Exception e) {
         e.printStackTrace ();
      }

      System.out.println (output);
   }
}
