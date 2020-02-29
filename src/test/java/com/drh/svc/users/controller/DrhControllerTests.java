package com.drh.svc.users.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.drh.svc.users.service.DrhService;
import com.drh.svc.users.support.DataGenerator;
import com.drh.svc.users.user.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DrhControllerTests {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Mock
  private DrhService drhService;

  private DrhController drhController;

  private List<User> dummyAllUsers;

  @Before
  public void setup() {
    drhController = new DrhController(drhService);

    dummyAllUsers = DataGenerator.getAllUsers();
  }

  @Test
  public void getAllUsers_Success() throws JsonProcessingException {
    String expectedResponse = DataGenerator.getAllUsersResponse();

    when(drhService.getAllUsers()).thenReturn(dummyAllUsers);

    List<User> response = drhController.getAllUsers();

    verify(drhService).getAllUsers();

    assertEquals(expectedResponse, OBJECT_MAPPER.writeValueAsString(response));
  }
}
