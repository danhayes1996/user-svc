package com.drh.svc.users.user.client;

import com.drh.svc.users.user.model.User;
import java.util.List;

public interface UserClient {

  List<User> getAll();

  User getUserById(int id);
}
