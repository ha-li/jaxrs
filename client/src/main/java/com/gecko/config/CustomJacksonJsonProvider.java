package com.gecko.config;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by hlieu on 06/11/17.
 */
@Provider
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class CustomJacksonJsonProvider
        // extends JacksonJaxbJsonProvider
        implements ContextResolver<ObjectMapper>
{
   private static ObjectMapper OBJ_MAPPER = new ObjectMapper ();

   static {
      AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector ();
      AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector ();
      AnnotationIntrospectorPair introspectorPair =
              new AnnotationIntrospectorPair(jacksonIntrospector, jaxbIntrospector);

      OBJ_MAPPER.configure (SerializationFeature.WRAP_ROOT_VALUE, true);
      OBJ_MAPPER.configure (DeserializationFeature.UNWRAP_ROOT_VALUE, true);
      OBJ_MAPPER.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
              .enable(SerializationFeature.INDENT_OUTPUT);
      OBJ_MAPPER.getDeserializationConfig ().withAppendedAnnotationIntrospector (introspectorPair);
      OBJ_MAPPER.getSerializationConfig ().withAppendedAnnotationIntrospector (introspectorPair);
   }

   public CustomJacksonJsonProvider () {
      super();
      //setMapper(OBJ_MAPPER);
   }

   //@Override
   public ObjectMapper getContext (Class<?> type) {
      return OBJ_MAPPER;
   }
}
