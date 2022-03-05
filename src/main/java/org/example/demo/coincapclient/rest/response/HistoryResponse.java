package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record HistoryResponse(BigDecimal priceUsd, Long time) {

}
