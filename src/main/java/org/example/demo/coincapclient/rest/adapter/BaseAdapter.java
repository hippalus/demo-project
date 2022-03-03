package org.example.demo.coincapclient.rest.adapter;

import lombok.RequiredArgsConstructor;
import org.example.demo.coincapclient.rest.request.common.RequestOptions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public abstract class BaseAdapter {

  protected static final String DELIMITER = "/";

  protected final RequestOptions requestOptions;
  protected final RestTemplate restTemplate;

  protected <T> T doGet(final String path, final Class<T> clazz) {
    final HttpEntity<T> heads = createHttpEntityWithHeader(requestOptions);
    final String url = requestOptions.getBaseUrl() + path;
    return restTemplate.exchange(url, HttpMethod.GET, heads, clazz).getBody();
  }
  
  protected static <T> HttpEntity<T> createHttpEntityWithHeader(RequestOptions options) {
    return new HttpEntity<>(createHttpHeaders(options));
  }

  protected static HttpHeaders createHttpHeaders(RequestOptions options) {
    final HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.ACCEPT_ENCODING, options.getEncoding());
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    if (options.getAccessToken() != null) {
      headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + options.getAccessToken());
    }
    return headers;
  }

}
