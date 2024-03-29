package org.example.demo.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final String key;
  private final String[] args;

  public BusinessException(String key) {
    super(key);
    this.key = key;
    args = new String[0];
  }

  public BusinessException(String key, String... args) {
    super(key);
    this.key = key;
    this.args = args;
  }

}
