package org.example.demo.exception;

public class DataNotFoundException extends BusinessException {

  public DataNotFoundException(String key) {
    super(key);
  }

  public DataNotFoundException(String key, String... args) {
    super(key, args);
  }
}