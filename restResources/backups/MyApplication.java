package com.gecko.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hlieu on 05/18/17.
 */
@ApplicationPath ("/")
public class MyApplication extends Application {
   @Override
   public Set<Class<?>> getClasses () {
      final Set<Class<?>> classes = new HashSet<Class<?>> ();
      classes.add(TicketResource.class);
      return classes;
   }

   @Override
   public Set<Object> getSingletons () {
      final Set<Object> instances = new HashSet<Object> ();
      return instances;
   }
}
