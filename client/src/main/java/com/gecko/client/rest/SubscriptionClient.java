package com.gecko.client.rest;

import com.gecko.domain.json.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * To run this client start up tomcat with subscription resource as specified by the context url below.
 * You can start up tomcat in DEBUG mode or regular mode.
 *
 * Then run this client which will call the service and get back a string response.
 *
 * Created by hlieu on 06/3/17.
 */
public class SubscriptionClient {

   public static void main (String[] args) {
      ClientBuilder jerseyBuilder = new JerseyClientBuilder ();

      Client jserseyClient = jerseyBuilder.build();
      WebTarget subscriptionTarget = jserseyClient.target("http://localhost:8080/restAdapter/subscription");
      WebTarget contextSubscriptTarget = subscriptionTarget.path("/context?name=bob");

      Response subscriptContextResponse = contextSubscriptTarget.request().get();
      String responseStr = subscriptContextResponse.readEntity (String.class);

      Subscription subscription = null;
      try {
         subscription = JsonUnMarshaller.unmarshall (responseStr, new Subscription ());
      } catch (Exception e) {
         e.printStackTrace ();
      }

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
