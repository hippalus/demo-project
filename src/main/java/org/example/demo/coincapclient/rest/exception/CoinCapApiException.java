package org.example.demo.coincapclient.rest.exception;

import lombok.Getter;

@Getter
public class CoinCapApiException extends RuntimeException {

  public CoinCapApiException(String message) {
    super(message);
  }

  public CoinCapApiException() {
  }
}
