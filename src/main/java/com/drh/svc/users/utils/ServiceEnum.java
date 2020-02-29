package com.drh.svc.users.utils;

import lombok.Getter;

public enum ServiceEnum {
  USER("user");

  @Getter
  private String service;

  ServiceEnum(String service) {
    this.service = service;
  }
}
