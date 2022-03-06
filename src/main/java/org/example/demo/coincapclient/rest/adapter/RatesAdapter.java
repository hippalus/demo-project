package org.example.demo.coincapclient.rest.adapter;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.coincapclient.rest.exception.CoinCapApiException;
import org.example.demo.coincapclient.rest.request.common.RequestOptions;
import org.example.demo.coincapclient.rest.response.AssetResponse;
import org.example.demo.coincapclient.rest.response.RateResponse;
import org.example.demo.coincapclient.rest.response.RatesResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RatesAdapter extends BaseAdapter {

  private static final String RATES = "/rates";
  private static final String COINCAP_API_RATES_CLIENT_ERROR = "coincapapi.rates.client.error";

  public RatesAdapter(RequestOptions requestOptions, RestTemplate restTemplate) {
    super(requestOptions, restTemplate);
  }

  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.rates.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.rates.retry-delay}")
  )
  public RatesResponse retrieve() {
    return doGet(RATES, RatesResponse.class);
  }

  @Recover
  public RatesResponse retrieve(final CoinCapApiException e) {
    log.error("Couldn't connect to coincap api to do retrieve rates", e);
    throw e;
  }

  @Recover
  public RatesResponse retrieve(final Exception e) {
    log.error("Couldn't connect to coincap api to do retrieve rates", e);
    throw new CoinCapApiException(COINCAP_API_RATES_CLIENT_ERROR);
  }


  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.rates.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.rates.retry-delay}")
  )
  public RateResponse retrieveById(final String id) {
    final String path = RATES + DELIMITER + Objects.requireNonNull(id);
    return doGet(path, RateResponse.class);
  }

  @Recover
  public RateResponse retrieveById(CoinCapApiException e, final String id) {
    log.error("Couldn't connect to coincap api to do retrieveById for {}", id, e);
    throw e;
  }

  @Recover
  public RateResponse retrieveById(final Exception e, final String id) {
    log.error("Couldn't connect to coincap api to do retrieveById for {}", id, e);
    throw new CoinCapApiException(COINCAP_API_RATES_CLIENT_ERROR);
  }
}
