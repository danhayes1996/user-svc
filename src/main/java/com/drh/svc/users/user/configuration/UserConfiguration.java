package com.drh.svc.users.user.configuration;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Setter
@Configuration
@ConfigurationProperties("user")
public class UserConfiguration {

  @Getter
  private String url;

  private int connectTimeout;
  private int readTimeout;
  private int retryAttempts;

  @Bean(name = "userRestOperations")
  public RestTemplate getUserRestTemplate() {
    SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
    httpRequestFactory.setConnectTimeout(this.connectTimeout);
    httpRequestFactory.setReadTimeout(readTimeout);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(httpRequestFactory);
    return restTemplate;
  }

  @Bean(name = "userRetryOperations")
  public RetryTemplate getUserRetryTemplate() {
    Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
    retryableExceptions.put(HttpClientErrorException.Unauthorized.class, false);
    retryableExceptions.put(HttpClientErrorException.BadRequest.class, false);
    retryableExceptions.put(HttpServerErrorException.InternalServerError.class, true);

    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(this.retryAttempts, retryableExceptions);

    RetryTemplate retryTemplate = new RetryTemplate();
    retryTemplate.setRetryPolicy(retryPolicy);
    return retryTemplate;
  }
}
