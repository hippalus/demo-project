package org.example.demo.coincapclient.rest.adapter;

import org.assertj.core.api.Assertions;
import org.example.demo.IT;
import org.example.demo.coincapclient.rest.request.MarketsRetrieveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IT
class MarketsAdapterIT {

  @Autowired
  private MarketsAdapter marketsAdapter;

  @Test
  void shouldRetrieveMarkets() {
    //given:
    final var retrieveRequest = MarketsRetrieveRequest.builder().build();
    //when:
    final var markets = marketsAdapter.retrieve(retrieveRequest);
    //then:
    Assertions.assertThat(markets).isNotNull();
    Assertions.assertThat(markets.data()).isNotNull();
    Assertions.assertThat(markets.data()).isNotEmpty();
  }

  @Test
  void shouldRetrieveMarketsByQueryParameters() {
    //given:
    final var retrieveRequest = MarketsRetrieveRequest.builder()
        .exchangeId("poloniex")
        .limit(200)
        .offset(1)
        .build();
    //when:
    final var markets = marketsAdapter.retrieve(retrieveRequest);
    //then:
    Assertions.assertThat(markets).isNotNull();
    Assertions.assertThat(markets.data()).isNotNull();
    Assertions.assertThat(markets.data()).isNotEmpty();
  }

  @Test
  void shouldRetrieveMarketsByQueryParameters2() {
    //given:
    final var retrieveRequest = MarketsRetrieveRequest.builder()
        .exchangeId("btcturk")
        .quoteSymbol("BTC")
        .limit(200)
        .offset(1)
        .build();
    //when:
    final var markets = marketsAdapter.retrieve(retrieveRequest);
    //then:
    Assertions.assertThat(markets).isNotNull();
    Assertions.assertThat(markets.data()).isNotNull();
    Assertions.assertThat(markets.data()).isNotEmpty();
  }


}