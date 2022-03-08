package org.example.demo.coincapclient.rest.adapter;

import org.assertj.core.api.Assertions;
import org.example.demo.IT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IT
class ExchangesAdapterIT {

  @Autowired
  private ExchangesAdapter exchangesAdapter;

  @Test
  void shouldRetrieveExchanges() {
    //when:
    var exchanges = exchangesAdapter.retrieve();
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
    final var exchange = exchangesAdapter.retrieveById(id);
    //then:
    Assertions.assertThat(exchange).isNotNull();
    Assertions.assertThat(exchange.data()).isNotNull();
    Assertions.assertThat(exchange.data()).hasNoNullFieldsOrProperties();
  }

}