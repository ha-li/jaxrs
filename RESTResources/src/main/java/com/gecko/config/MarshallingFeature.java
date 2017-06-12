package com.gecko.config;

import com.gecko.provider.CustomJacksonJsonProvider;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * Created by hlieu on 05/22/17.
 */
public class MarshallingFeature implements Feature {

   @Override
   public boolean configure (FeatureContext context) {
      // an empty feature right now
      context.register (CustomJacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
      return true;
   }

}
