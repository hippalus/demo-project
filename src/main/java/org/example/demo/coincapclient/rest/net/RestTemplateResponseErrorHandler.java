package org.example.demo.coincapclient.rest.net;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import java.io.IOException;
import org.example.demo.coincapclient.rest.exception.CoinCapApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return (httpResponse.getStatusCode().series() == CLIENT_ERROR || httpResponse.getStatusCode().series() == SERVER_ERROR);
  }

  @Override//TODO
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
      throw new CoinCapApiException();
    } else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
      if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
        throw new CoinCapApiException();
      }
    }
  }
}
