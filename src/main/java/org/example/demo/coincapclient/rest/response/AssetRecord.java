package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record AssetRecord(String id,
                          String rank,
                          String symbol,
                          String name,
                          BigDecimal supply,
                          BigDecimal maxSupply,
                          BigDecimal marketCapUsd,
                          BigDecimal volumeUsd24Hr,
                          BigDecimal priceUsd,
                          BigDecimal changePercent24Hr,
                          BigDecimal vwap24Hr,
                          String explorer) {

}
