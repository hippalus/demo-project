package org.example.demo.coincapclient.rest.adapter;

import org.assertj.core.api.Assertions;
import org.example.demo.coincapclient.rest.response.ExchangeResponse;
import org.example.demo.coincapclient.rest.response.ExchangesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExchangesAdapterTest extends AbstractIntegrationTest {

  @Autowired
  private ExchangesAdapter exchangesAdapter;

  @Test
  void shouldRetrieveExchanges() {
    //when:
    ExchangesResponse exchanges = exchangesAdapter.retrieve();
    //then:
    Assertions.assertThat(exchanges).isNotNull();
    Assertions.assertThat(exchanges.data()).isNotNull();
    Assertions.assertThat(exchanges.data()).isNotEmpty();
  }

  @Test
  void shouldRetrieveExchangeById() {
    //given:
    final var id = "kraken";
    //when:
    final ExchangeResponse exchange = exchangesAdapter.retrieveById(id);
    //then:
    Assertions.assertThat(exchange).isNotNull();
    Assertions.assertThat(exchange.data()).isNotNull();
    Assertions.assertThat(exchange.data()).hasNoNullFieldsOrProperties();
  }

}