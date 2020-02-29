package com.drh.svc.users.service;

import static org.slf4j.LoggerFactory.getLogger;

import com.drh.svc.users.user.model.User;
import com.drh.svc.users.user.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrhServiceImpl implements DrhService {
  private static final Logger LOGGER = getLogger(DrhServiceImpl.class);

  private final UserService userService;

  @Autowired
  public DrhServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public List<User> getAllUsers() {
    LOGGER.info("Calling User Service to get all users.");
    return userService.getAll();
  }
}
