package com.gecko.client.rest;

import com.gecko.domain.Subscription;
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

      Subscription subscription = new Subscription ();
      Response response = target
              .request(MediaType.APPLICATION_XML)
              .accept(MediaType.APPLICATION_XML)
              .post(Entity.xml (subscription));


      //String responseStr = (String) response.readEntity (String.class);

      Subscription responseStr = (Subscription) response.readEntity (Subscription.class);
      //System.out.println ("id: " + responseStr.getId () + ", user: " + responseStr.getUser ());

      /* String output = null;
      try {
         output = JsonUnMarshaller.formatForOutput (responseStr);
      } catch (Exception e) {
         e.printStackTrace ();
      } */

      //System.out.println (output);
   }
}
