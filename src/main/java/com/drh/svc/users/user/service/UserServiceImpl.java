package com.drh.svc.users.user.service;

import com.drh.svc.users.user.client.UserClient;
import com.drh.svc.users.user.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserClient userClient;

  @Autowired
  public UserServiceImpl(UserClient userClient) {
    this.userClient = userClient;
  }

  @Override
  public List<User> getAll() {
    return userClient.getAll();
  }
}
