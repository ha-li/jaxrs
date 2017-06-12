package com.gecko.provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * Created by hlieu on 06/11/17.
 */
@Provider
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CustomJacksonJsonProvider extends JacksonJaxbJsonProvider {
   private static ObjectMapper OBJ_MAPPER = new ObjectMapper ();

   static {
      OBJ_MAPPER.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
              .enable(SerializationFeature.INDENT_OUTPUT);

   }

   public CustomJacksonJsonProvider () {
      super();
      setMapper(OBJ_MAPPER);
   }
}
