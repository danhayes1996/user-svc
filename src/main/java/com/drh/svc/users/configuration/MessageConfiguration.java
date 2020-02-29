package com.drh.svc.users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfiguration {

  @Bean(name = "messageSource")
  public ReloadableResourceBundleMessageSource getMessages() {
    ReloadableResourceBundleMessageSource messageSource
        = new ReloadableResourceBundleMessageSource();

    messageSource.setBasename("classpath:/locale/messages");

    return messageSource;
  }
}
