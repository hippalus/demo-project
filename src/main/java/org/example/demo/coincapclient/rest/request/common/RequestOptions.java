package org.example.demo.coincapclient.rest.request.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class RequestOptions {

  private String accessToken;//="5859461c-df0a-42b3-a0ba-14410c4fd2b6";
  private String encoding = "gzip";
  private String baseUrl = "https://api.coincap.io/v2";

}
