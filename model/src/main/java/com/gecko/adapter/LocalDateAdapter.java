package com.gecko.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by hlieu on 06/11/17.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

   @Override
   public LocalDate unmarshal (String input) throws Exception {
      return LocalDate.parse(input);
   }

   @Override
   public String marshal (LocalDate input) throws Exception {
      return input.toString();
   }
}
