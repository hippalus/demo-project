package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record AssetMarket(String exchangeId,
                          String baseId,
                          String quoteId,
                          String baseSymbol,
                          String quoteSymbol,
                          BigDecimal volumeUsd24Hr,
                          BigDecimal priceUsd,
                          BigDecimal volumePercent) {

}
