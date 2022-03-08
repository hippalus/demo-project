package org.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class AbstractRestIT {

  @Autowired
  protected TestRestTemplate testRestTemplate;

  @LocalServerPort
  protected Integer port;


  protected static HttpHeaders createHttpHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip");
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }
}
