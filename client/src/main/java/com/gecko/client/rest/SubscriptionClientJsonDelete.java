package com.gecko.client.rest;

import com.gecko.domain.Subscription;
import com.gecko.json.bind.JsonUnMarshaller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.gecko.schema.subscription.v1.Subscription;

/**
 * Created by hlieu on 06/5/17.
 */
public class SubscriptionClientJsonDelete
        extends SubscriptionClient
{

   public static void main (String[] args) {

      SubscriptionClientJsonDelete jsonDelete = new SubscriptionClientJsonDelete();

      Response jrsyResponse = jsonDelete.resourcePath ("/subscriber/{id}/{user}")
              .resolveTemplate("id", "123")
              .resolveTemplate("user", "teahouseFresca")
              .queryParam("name", "bob")
              .request(MediaType.APPLICATION_JSON)
              .delete(Response.class);

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
