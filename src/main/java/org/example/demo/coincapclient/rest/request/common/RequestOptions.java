package org.example.demo.coincapclient.rest.request.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "coincap-client.request-options")
public class RequestOptions {

  private String accessToken;
  private String encoding;
  private String baseUrl;

}
