package com.gecko.resources;

import com.gecko.domain.principal.Principal;
import com.gecko.schema.subscription.v1.Configuration;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

/**
 * Created by hlieu on 06/11/17.
 */
@Path ("config")
@Produces ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes ({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ConfigurationResource {

   @POST
   @Path ("add")
   public Response createConfiguration (Configuration newConf) {
      Configuration configuration = new Configuration ();
      configuration.setName ("USE_JMS");
      configuration.setValue (Boolean.TRUE.toString ());
      configuration.setCreatedDate (LocalDate.now ());
      configuration.setCreatedBy (Principal.SYSTEM.name());
      configuration.setModifiedDate (configuration.getCreatedDate ());
      configuration.setModifiedBy (configuration.getCreatedBy ());

      return Response.ok().entity(configuration).build();
   }
}
