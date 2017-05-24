package com.gecko.domain.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hlieu on 05/21/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
   private String msg;

   public String getMsg () {
      return msg;
   }

   public void setMsg (String msg) {
      this.msg = msg;
   }
}
