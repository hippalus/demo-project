package org.example.demo.coincapclient.rest.adapter;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.coincapclient.rest.exception.CoinCapApiException;
import org.example.demo.coincapclient.rest.request.common.RequestOptions;
import org.example.demo.coincapclient.rest.response.ExchangeResponse;
import org.example.demo.coincapclient.rest.response.ExchangesResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ExchangesAdapter extends BaseAdapter {

  private static final String EXCHANGES = "/exchanges";
  private static final String COINCAP_API_EXCHANGES_CLIENT_ERROR = "coincapapi.exchanges.client.error";

  public ExchangesAdapter(RequestOptions requestOptions, RestTemplate restTemplate) {
    super(requestOptions, restTemplate);
  }

  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.exchanges.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.exchanges.retry-delay}")
  )
  public ExchangesResponse retrieve() {
    return doGet(EXCHANGES, ExchangesResponse.class);
  }

  @Recover
  public ExchangesResponse retrieve(final CoinCapApiException e) {
    log.error("Couldn't connect to coincap api to do retrieve exchanges", e);
    throw e;
  }

  @Recover
  public ExchangesResponse retrieve(final Exception e) {
    log.error("Couldn't connect to coincap api to do retrieve exchanges", e);
    throw new CoinCapApiException(COINCAP_API_EXCHANGES_CLIENT_ERROR);
  }


  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.exchanges.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.exchanges.retry-delay}")
  )
  public ExchangeResponse retrieveById(final String id) {
    final String path = EXCHANGES + DELIMITER + Objects.requireNonNull(id);
    return doGet(path, ExchangeResponse.class);
  }

  @Recover
  public ExchangeResponse retrieveById(CoinCapApiException e, final String id) {
    log.error("Couldn't connect to coincap api to do retrieveById for {}", id, e);
    throw e;
  }

  @Recover
  public ExchangeResponse retrieveById(final Exception e, final String id) {
    log.error("Couldn't connect to coincap api to do retrieveById for {}", id, e);
    throw new CoinCapApiException(COINCAP_API_EXCHANGES_CLIENT_ERROR);
  }
}
