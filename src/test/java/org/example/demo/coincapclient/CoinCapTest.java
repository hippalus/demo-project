package org.example.demo.coincapclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoinCapTest {

  @Autowired
  private CoinCap coinCap;

  @Test
  void shouldNotNullAdapters() {
    Assertions.assertNotNull(coinCap.assets());
    Assertions.assertNotNull(coinCap.candles());
    Assertions.assertNotNull(coinCap.exchanges());
    Assertions.assertNotNull(coinCap.markets());
    Assertions.assertNotNull(coinCap.rates());
  }
}