package com.drh.svc.users.controller;

import static org.slf4j.LoggerFactory.getLogger;

import com.drh.svc.users.configuration.PathsConfiguration;
import com.drh.svc.users.service.DrhService;
import com.drh.svc.users.user.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathsConfiguration.USERS_BASE_PATH)
public class DrhController {
  private static final Logger LOGGER = getLogger(DrhController.class);

  private final DrhService drhService;

  @Autowired
  public DrhController(DrhService drhService) {
    this.drhService = drhService;
  }

  @GetMapping(PathsConfiguration.ALL_USERS)
  public List<User> getAllUsers() {
    LOGGER.info("Received request to get all users");
    return drhService.getAllUsers();
  }

  @GetMapping(PathsConfiguration.GET_USER_BY_ID)
  public User getUserById(@PathVariable("id") int id) {
    return null;
  }
}
