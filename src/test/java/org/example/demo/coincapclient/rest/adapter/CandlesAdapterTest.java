package org.example.demo.coincapclient.rest.adapter;

import org.assertj.core.api.Assertions;
import org.example.demo.coincapclient.rest.request.CandlesRetrieveRequest;
import org.example.demo.coincapclient.rest.response.CandlesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CandlesAdapterTest extends AbstractIntegrationTest {

  @Autowired
  private CandlesAdapter candlesAdapter;

  @Test
  void shouldThrowAnException_whenRetrieveCandlesBy_nullQueryParams() {
    Assertions.assertThatNullPointerException().isThrownBy(() -> CandlesRetrieveRequest.builder().build());
  }

  @Test
  void shouldRetrieveCandlesByQueryParameters() {
    //given:
    final var retrieveRequest = CandlesRetrieveRequest.builder()
        .exchange("poloniex")
        .interval("h8")
        .baseId("ethereum")
        .quoteId("bitcoin")
        .build();
    //when:
    final CandlesResponse candles = candlesAdapter.retrieve(retrieveRequest);
    //then:
    Assertions.assertThat(candles).isNotNull();
    Assertions.assertThat(candles.data()).isNotNull();
    Assertions.assertThat(candles.data()).isNotEmpty();
  }
}