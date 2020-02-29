package com.drh.svc.users.user.client;

import static org.slf4j.LoggerFactory.getLogger;

import com.drh.svc.users.exception.DrhException;
import com.drh.svc.users.user.configuration.UserConfiguration;
import com.drh.svc.users.user.model.User;
import com.drh.svc.users.utils.ReaderUtils;
import com.drh.svc.users.utils.ServiceEnum;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

@Component
public class UserClientImpl implements UserClient {
  private static final Logger LOGGER = getLogger(UserClientImpl.class);

  private final UserConfiguration userConfiguration;
  private final RestOperations restOperations;
  private final ReloadableResourceBundleMessageSource messageSource;
  private final ReaderUtils readerUtils;

  public UserClientImpl(UserConfiguration userConfiguration,
                        @Qualifier("userRestOperations") RestOperations restOperations,
                        ReloadableResourceBundleMessageSource messageSource,
                        ReaderUtils readerUtils) {
    this.userConfiguration = userConfiguration;
    this.restOperations = restOperations;
    this.messageSource = messageSource;
    this.readerUtils = readerUtils;
  }

  @Override
  public List<User> getAll() {
    LOGGER.info("Started search for all users.");
    List<User> responseBody = null;

    try {
      ParameterizedTypeReference<List<User>> typeRef
          = new ParameterizedTypeReference<List<User>>() {};

      ResponseEntity<List<User>> responseEntity = restOperations.exchange(
          userConfiguration.getUrl(),
          HttpMethod.GET,
          null,
          typeRef);

      responseBody = responseEntity.getBody();

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      this.readerUtils.readRestError(ServiceEnum.USER, e);
    } catch (RestClientException restException) {
      LOGGER.error("refused connection: {}", restException.getMessage());
      throw new DrhException(messageSource.getMessage(
          "com.user.connection", null, Locale.UK), HttpStatus.SERVICE_UNAVAILABLE);
    }
    LOGGER.info("Received response from get all users.");
    return responseBody;
  }

  @Override
  public User getUserById(int id) {
    return null;
  }
}
