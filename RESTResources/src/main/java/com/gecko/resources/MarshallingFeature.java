package com.gecko.resources;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Created by hlieu on 05/22/17.
 */
public class MarshallingFeature implements Feature {

   @Override
   public boolean configure (FeatureContext context) {
      // an empty feature right now
      return true;
   }

}
