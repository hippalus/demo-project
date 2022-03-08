package org.example.demo.coincapclient.rest.adapter;

import org.assertj.core.api.Assertions;
import org.example.demo.IT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IT
class RatesAdapterIT {

  @Autowired
  private RatesAdapter ratesAdapter;

  @Test
  void shouldRetrieveRates() {
    //when:
    final var rates = ratesAdapter.retrieve();
    //then:
    Assertions.assertThat(rates).isNotNull();
    Assertions.assertThat(rates.data()).isNotNull();
    Assertions.assertThat(rates.data()).isNotEmpty();
  }

  @Test
  void shouldRetrieveRateById() {
    //given:
    final var id = "bitcoin";
    //when:
    final var rate = ratesAdapter.retrieveById(id);
    //then:
    Assertions.assertThat(rate).isNotNull();
    Assertions.assertThat(rate.data()).isNotNull();
    Assertions.assertThat(rate.data()).hasNoNullFieldsOrProperties();
  }

}