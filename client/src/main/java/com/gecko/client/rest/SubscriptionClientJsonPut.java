package com.gecko.client.rest;

import com.gecko.domain.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/5/17.
 */
public class SubscriptionClientJsonPut
        extends SubscriptionClient
{

   public static void main (String[] args) {
      SubscriptionClientJsonPut jsonPut = new SubscriptionClientJsonPut ();
      WebTarget subscriptionTarget = jsonPut.resourcePath("/subscriber/teahouseFresca");

      Subscription param = new Subscription ();
      param.setUser("Mysterious User");
      param.setId ("123123");
      Response response = subscriptionTarget.request(MediaType.APPLICATION_JSON).put(Entity.json (param));
      Subscription subscription = (Subscription) response.readEntity (Subscription.class);

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
