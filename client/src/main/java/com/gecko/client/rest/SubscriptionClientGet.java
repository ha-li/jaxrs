package com.gecko.client.rest;

import com.gecko.domain.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// import com.gecko.schema.subscription.v1.Subscription;

/**
 * To run this client start up tomcat with subscription resource as specified by the context url below.
 * You can start up tomcat in DEBUG mode or regular mode.
 *
 * Then run this client which will call the service and get back a string response.
 *
 * Created by hlieu on 06/3/17.
 */
public class SubscriptionClientGet extends SubscriptionClient {

   public static void main (String[] args) {

      SubscriptionClientGet getPost = new SubscriptionClientGet();

      WebTarget contextSubscriptTarget = getPost.resourcePath("/context?name=bob");

      Response response = contextSubscriptTarget.request(MediaType.APPLICATION_JSON).get();
      Subscription responseStr = (Subscription) response.readEntity (Subscription.class);

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
