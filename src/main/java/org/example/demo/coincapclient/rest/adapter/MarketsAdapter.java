package org.example.demo.coincapclient.rest.adapter;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.coincapclient.rest.exception.CoinCapApiException;
import org.example.demo.coincapclient.rest.request.MarketsRetrieveRequest;
import org.example.demo.coincapclient.rest.request.common.RequestOptions;
import org.example.demo.coincapclient.rest.request.common.RequestQueryParamBuilder;
import org.example.demo.coincapclient.rest.response.MarketsResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class MarketsAdapter extends BaseAdapter {

  private static final String MARKETS = "/markets";
  private static final String COINCAP_API_MARKETS_CLIENT_ERROR = "coincapapi.markets.client.error";

  public MarketsAdapter(RequestOptions requestOptions, RestTemplate restTemplate) {
    super(requestOptions, restTemplate);
  }

  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.markets.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.markets.retry-delay}")
  )
  public MarketsResponse retrieve(MarketsRetrieveRequest request) {
    final String queryParam = RequestQueryParamBuilder.buildQueryParam(request);
    final String path = MARKETS + queryParam;
    return doGet(path, MarketsResponse.class);
  }

  @Recover
  public MarketsResponse retrieve(final CoinCapApiException e, final MarketsRetrieveRequest request) {
    log.error("Couldn't connect to coincap api to do retrieve markets for {}", request, e);
    throw e;
  }

  @Recover
  public MarketsResponse retrieve(final Exception e, final MarketsRetrieveRequest request) {
    log.error("Couldn't connect to coincap api to do retrieve markets for {}", request, e);
    throw new CoinCapApiException(COINCAP_API_MARKETS_CLIENT_ERROR);
  }


}
