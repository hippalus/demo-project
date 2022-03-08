package org.example.demo.coincapclient.rest.exception;

import lombok.Getter;
import org.example.demo.common.exception.BusinessException;

@Getter
public class CoinCapApiException extends BusinessException {

  public CoinCapApiException(String key) {
    super(key);
  }

  public CoinCapApiException(String key, String... args) {
    super(key, args);
  }
}
