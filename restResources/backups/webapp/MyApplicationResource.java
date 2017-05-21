package com.gecko.resources;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

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
   }
}
