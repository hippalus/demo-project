package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record ExchangeRecord(
    String exchangeId,
    String name,
    String rank,
    BigDecimal percentTotalVolume,
    BigDecimal volumeUsd,
    Integer tradingPairs,
    Boolean socket,
    String exchangeUrl,
    Long updated) {

}
