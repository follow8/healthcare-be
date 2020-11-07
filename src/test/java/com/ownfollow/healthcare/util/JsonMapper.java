package com.ownfollow.healthcare.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
  private static ObjectMapper mapper;
  static {
    mapper = new ObjectMapper();
  }
  private JsonMapper() {
    //default constructor
  }

  public static String objAsJsonString(final Object value) {
    try {
      return mapper.writeValueAsString(value);
    }catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

  public static <T> T jsonStringAsObj(final String str, Class<T> clazz) {
    try {
      return mapper.readValue(str, clazz);
    }catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}
