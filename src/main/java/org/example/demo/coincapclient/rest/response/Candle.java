package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record Candle(BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal volume, Long period) {

}
