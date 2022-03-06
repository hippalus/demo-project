package org.example.demo.coincapclient.rest.adapter;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.coincapclient.rest.exception.CoinCapApiException;
import org.example.demo.coincapclient.rest.request.AssetHistoryRequest;
import org.example.demo.coincapclient.rest.request.AssetMarketsRequest;
import org.example.demo.coincapclient.rest.request.AssetsRetrieveRequest;
import org.example.demo.coincapclient.rest.request.common.RequestOptions;
import org.example.demo.coincapclient.rest.request.common.RequestQueryParamBuilder;
import org.example.demo.coincapclient.rest.response.AssetHistoryResponse;
import org.example.demo.coincapclient.rest.response.AssetMarketsResponse;
import org.example.demo.coincapclient.rest.response.AssetsResponse;
import org.example.demo.coincapclient.rest.response.AssetResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AssetsAdapter extends BaseAdapter {

  private static final String ASSETS = "/assets";
  private static final String COINCAP_API_ASSETS_CLIENT_ERROR = "coincapapi.assets.client.error";

  public AssetsAdapter(RequestOptions requestOptions, RestTemplate restTemplate) {
    super(requestOptions, restTemplate);
  }

  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.assets.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.assets.retry-delay}")
  )
  public AssetsResponse retrieve(final AssetsRetrieveRequest request) {
    final String queryParam = RequestQueryParamBuilder.buildQueryParam(request);
    final String path = ASSETS + queryParam;
    return doGet(path, AssetsResponse.class);
  }

  @Recover
  public AssetMarketsResponse retrieve(final CoinCapApiException e, final AssetsRetrieveRequest request) {
    log.error("Couldn't connect to coincap api to do retrieve for {}", request, e);
    throw e;
  }

  @Recover
  public AssetMarketsResponse retrieve(final Exception e, final AssetsRetrieveRequest request) {
    log.error("Couldn't connect to coincap api to do retrieve for {}", request, e);
    throw new CoinCapApiException(COINCAP_API_ASSETS_CLIENT_ERROR);
  }


  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.assets.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.assets.retry-delay}")
  )
  public AssetResponse retrieveById(final String id) {
    final String path = ASSETS + DELIMITER + Objects.requireNonNull(id);
    return doGet(path, AssetResponse.class);
  }

  @Recover
  public AssetResponse retrieveById(CoinCapApiException e, final String id) {
    log.error("Couldn't connect to coincap api to do retrieveById for {}", id, e);
    throw e;
  }

  @Recover
  public AssetResponse retrieveById(final Exception e, final String id) {
    log.error("Couldn't connect to coincap api to do retrieveById for {}", id, e);
    throw new CoinCapApiException(COINCAP_API_ASSETS_CLIENT_ERROR);
  }


  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.assets.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.assets.retry-delay}")
  )
  public AssetHistoryResponse retrieveHistory(final String id, final AssetHistoryRequest request) {
    final String validId = Objects.requireNonNull(id);
    final String queryParam = RequestQueryParamBuilder.buildQueryParam(request);
    final String path = ASSETS + DELIMITER + validId + DELIMITER + "history" + DELIMITER + queryParam;
    return doGet(path, AssetHistoryResponse.class);
  }

  @Recover
  public AssetHistoryResponse retrieveHistory(final CoinCapApiException e, final String id, final AssetHistoryRequest request) {
    log.error("Couldn't connect to coincap api to do retrieveHistory for {} {}", id, request, e);
    throw e;
  }

  @Recover
  public AssetHistoryResponse retrieveHistory(final Exception e, final String id, final AssetHistoryRequest request) {
    log.error("Couldn't connect to coincap api to do retrieveHistory for {} {}", id, request, e);
    throw new CoinCapApiException(COINCAP_API_ASSETS_CLIENT_ERROR);
  }


  @Retryable(
      value = {Exception.class},
      maxAttemptsExpression = "${coincap-client.adapters.assets.retry-attempts}",
      backoff = @Backoff(delayExpression = "${coincap-client.adapters.assets.retry-delay}")
  )
  public AssetMarketsResponse retrieveAssetMarkets(final String id, AssetMarketsRequest request) {
    final String validId = Objects.requireNonNull(id);
    final String queryParam = RequestQueryParamBuilder.buildQueryParam(request);
    final String path = ASSETS + DELIMITER + validId + DELIMITER + "markets" + DELIMITER + queryParam;
    return doGet(path, AssetMarketsResponse.class);
  }

  @Recover
  public AssetMarketsResponse retrieveAssetMarkets(final CoinCapApiException e, final String id, AssetMarketsRequest request) {
    log.error("Couldn't connect to coincap api to do retrieveAssetMarkets for {} {}", id, request, e);
    throw e;
  }

  @Recover
  public AssetMarketsResponse retrieveAssetMarkets(final Exception e, final String id, AssetMarketsRequest request) {
    log.error("Couldn't connect to coincap api to do retrieveAssetMarkets for {} {}", id, request, e);
    throw new CoinCapApiException(COINCAP_API_ASSETS_CLIENT_ERROR);
  }
}
