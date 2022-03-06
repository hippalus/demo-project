package org.example.demo.coincapclient.rest.adapter;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.coincapclient.rest.exception.CoinCapApiException;
import org.example.demo.coincapclient.rest.request.CandlesRetrieveRequest;
import org.example.demo.coincapclient.rest.request.common.RequestOptions;
import org.example.demo.coincapclient.rest.request.common.RequestQueryParamBuilder;
import org.example.demo.coincapclient.rest.response.CandlesResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CandlesAdapter extends BaseAdapter {

  private static final String CANDLES = "/candles";
  private static final String COINCAP_API_CANDLES_CLIENT_ERROR = "coincapapi.candles.client.error";

  public CandlesAdapter(RequestOptions requestOptions, RestTemplate restTemplate) {
    super(requestOptions, restTemplate);
  }

  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.candles.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.candles.retry-delay}")
  )
  public CandlesResponse retrieve(CandlesRetrieveRequest request) {
    final String queryParam = RequestQueryParamBuilder.buildQueryParam(request);
    final String path = CANDLES + queryParam;
    return doGet(path, CandlesResponse.class);
  }

  @Recover
  public CandlesResponse retrieve(final CoinCapApiException e, final CandlesRetrieveRequest request) {
    log.error("Couldn't connect to coincap api to do retrieve candles for {}", request, e);
    throw e;
  }

  @Recover
  public CandlesResponse retrieve(final Exception e, final CandlesRetrieveRequest request) {
    log.error("Couldn't connect to coincap api to do retrieve candles for {}", request, e);
    throw new CoinCapApiException(COINCAP_API_CANDLES_CLIENT_ERROR);
  }


}
