package com.gecko.client.rest;

import com.gecko.domain.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/4/17.
 */
public class SubscriptionClientJsonPost
        extends SubscriptionClient
{

   public static void main (String[] args) {

      SubscriptionClientJsonPost jsonPost = new SubscriptionClientJsonPost();

      String id = "teahouseFresca";
      WebTarget restTarget = jsonPost.resourcePath ("subscriber/{id}")
              .resolveTemplate ("id", id);

      Subscription postParam = new Subscription ();
      postParam.setId (id);

      // on the client side, request is what the response mime type
      // should come back as (here it's application/json)
      Response response = restTarget
              .request(MediaType.APPLICATION_JSON)
              //.accept(MediaType.APPLICATION_JSON)
              .post(Entity.json (postParam));

      String output = null;
      try {
         Subscription subscription = (Subscription) response.readEntity (Subscription.class);

         // can also get the string entity and unmarshall that
         // String strData = (String) response.readEntity (String.class);
         // Subscription subscription = JsonUnMarshaller.unmarshall (strData, new Subscription());
         System.out.println ("id: " + subscription.getId () + ", user: " + subscription.getUser ());

         output = JsonUnMarshaller.formatForOutput (subscription);
      } catch (Exception e) {
         e.printStackTrace ();
      }

      System.out.println (output);
   }
}
