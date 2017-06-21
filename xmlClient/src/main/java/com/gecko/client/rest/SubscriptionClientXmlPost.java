package com.gecko.client.rest;

import com.gecko.schema.subscription.v1.Subscription;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/5/17.
 *
 * TODO: This does not work. Still to figure out.
 */
public class SubscriptionClientXmlPost {
   public static void main (String[] args) {
      ClientBuilder jerseyBuilder = new JerseyClientBuilder ();

      Client jserseyClient = jerseyBuilder
              //.register(CustomJacksonJsonProvider.class)
              .build();
      WebTarget subscriptionTarget = jserseyClient.target("http://localhost:8080/restAdapter/subscription");
      WebTarget target = subscriptionTarget.path("/subscriber/teahouseFresca");
      //WebTarget xmlTarget = target.register(MarshallingFeature.class);

      Subscription imput = new Subscription ();
      Response response = target
              .request(MediaType.APPLICATION_XML)
              .accept(MediaType.APPLICATION_XML)
              .post(Entity.xml (imput));

      Subscription subscription = (Subscription) response.readEntity (Subscription.class);
      System.out.println ("id: " + subscription.getId () + ", user: " + subscription.getUser ());

      String output = null;
      /* try {
         output = JsonUnMarshaller.formatForOutput (responseStr);
      } catch (Exception e) {
         e.printStackTrace ();
      } */

      System.out.println (subscription);
   }
}
