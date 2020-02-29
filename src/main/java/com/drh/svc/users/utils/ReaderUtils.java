package com.drh.svc.users.utils;

import static org.slf4j.LoggerFactory.getLogger;

import com.drh.svc.users.exception.DrhException;
import java.util.Locale;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class ReaderUtils {
  private static final Logger LOGGER = getLogger(ReaderUtils.class);

  private final ReloadableResourceBundleMessageSource messageSource;

  @Autowired
  public ReaderUtils(ReloadableResourceBundleMessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public void readRestError(ServiceEnum serviceEnum, HttpStatusCodeException e) {
    LOGGER.error("{} - Threw {} Exception with message: \"{}\"",
        serviceEnum.getService(),
        e.getLocalizedMessage(),
        e.getMessage());

    switch (e.getStatusCode()) {
      case NOT_FOUND:
        throw new DrhException(messageSource.getMessage(
            "error." + serviceEnum.getService() + ".notFound", null, Locale.UK),
            HttpStatus.NOT_FOUND);
      case BAD_REQUEST:
        throw new DrhException(messageSource.getMessage(
            "error." + serviceEnum.getService() + ".badRequest", null, Locale.UK),
            HttpStatus.BAD_REQUEST);
      case UNAUTHORIZED:
        throw new DrhException(messageSource.getMessage(
            "error." + serviceEnum.getService() + ".unauthorized", null, Locale.UK),
            HttpStatus.UNAUTHORIZED);
      case BAD_GATEWAY:
        throw new DrhException(messageSource.getMessage(
            "error." + serviceEnum.getService() + ".badGateway", null, Locale.UK),
            HttpStatus.BAD_GATEWAY);
      case INTERNAL_SERVER_ERROR:
        throw new DrhException(messageSource.getMessage(
            "error." + serviceEnum.getService() + ".internalError", null, Locale.UK),
            HttpStatus.INTERNAL_SERVER_ERROR);
      default:
        throw new DrhException(
            serviceEnum.getService() + " Service Exception: " + e.getMessage(), e);
    }
  }
}
