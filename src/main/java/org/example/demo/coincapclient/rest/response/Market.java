package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record Market(String exchangeId,
                     String rank,
                     String baseSymbol,
                     String baseId,
                     String quoteSymbol,
                     String quoteId,
                     BigDecimal priceQuote,
                     BigDecimal priceUsd,
                     BigDecimal volumeUsd24Hr,
                     BigDecimal percentExchangeVolume,
                     Long tradesCount24Hr,
                     Long updated) {
  
}
