package com.gecko.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by hlieu on 05/18/17.
 */
//@ApplicationPath ("/")
public class MyApplicationResource extends ResourceConfig {

   /**
    * Use this class when you want to do some kind of
    * configuration prior to loading the classes in the
    * specified packages
    */

   public MyApplicationResource () {

      packages("com.gecko.resources");
      packages("com.gecko.domain.json")
              .register(ObjectMapper.class)      // using the default object mapper
              .register(JacksonFeature.class)    // with jackson (json) support
              .register(MarshallingFeature.class);
   }
}
