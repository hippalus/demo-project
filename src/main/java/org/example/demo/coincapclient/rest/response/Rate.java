package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;

public record Rate(String id, String symbol, String currencySymbol, String type, BigDecimal rateUsd) {

}
