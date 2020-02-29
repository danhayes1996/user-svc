package com.drh.svc.users.support;

import static java.util.Arrays.asList;

import com.drh.svc.users.user.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class DataGenerator {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static User[] ALL_USERS;
  private static String ALL_USERS_RESPONSE;

  static {
    ClassLoader cl = DataGenerator.class.getClassLoader();
    InputStream allUserResIs = cl.getResourceAsStream("data/allUsersResponse.json");

    try {
      ALL_USERS = OBJECT_MAPPER.readValue(allUserResIs, User[].class);
      ALL_USERS_RESPONSE = OBJECT_MAPPER.writeValueAsString(ALL_USERS);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private DataGenerator() { }

  private static <T> T clone(Object obj, Class<T> clazz) {
    try {
      return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(obj), clazz);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<User> getAllUsers() {
    return asList(clone(ALL_USERS, User[].class));
  }

  public static String getAllUsersResponse() {
    return ALL_USERS_RESPONSE;
  }
}
