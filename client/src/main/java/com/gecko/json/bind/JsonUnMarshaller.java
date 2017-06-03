package com.gecko.json.bind;

import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by hlieu on 06/3/17.
 */
public class JsonUnMarshaller {
   private static com.fasterxml.jackson.databind.ObjectMapper JACKSON_OBJ_MAPPER;
   private static com.fasterxml.jackson.databind.type.CollectionType COLLECTION_TYPE;

   static {
      JACKSON_OBJ_MAPPER = new com.fasterxml.jackson.databind.ObjectMapper ();
      JACKSON_OBJ_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
   }

   public static <T> List<T> unmarshallAll (String fileName, Collection<T> collection, T t) throws IOException {
      COLLECTION_TYPE = JACKSON_OBJ_MAPPER.getTypeFactory ().constructCollectionType (List.class, t.getClass());

      return JACKSON_OBJ_MAPPER.readValue(new File (fileName), COLLECTION_TYPE);
   }

   public static <T> T unmarshall (String jsonStringValue, T t) throws IOException {
      return (T) JACKSON_OBJ_MAPPER.readValue (jsonStringValue, t.getClass());
   }

   public static <T> String formatForOutput (T t) throws IOException {
      return JACKSON_OBJ_MAPPER.writeValueAsString (t);
   }
}
