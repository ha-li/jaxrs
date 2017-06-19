package com.gecko.client.rest;

import com.gecko.domain.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hlieu on 06/5/17.
 */
public class SubscriptionClientGetWithCookie
        extends SubscriptionClient
{

   public static void main (String[] args) {

      SubscriptionClientGetWithCookie jsonGet = new SubscriptionClientGetWithCookie ();
      WebTarget restTarget = jsonGet.resourcePath ("/websub/{id}")
              .resolveTemplate("id", "Jamica");

      Cookie subIdCookie = new Cookie ("sub-id", "app-id-client");
      Cookie realmCookie = new Cookie ("realm", "hidden-realm");

      Response subscriptContextResponse = restTarget
              .request(MediaType.APPLICATION_JSON)
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
