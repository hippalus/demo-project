package org.example.demo.coincapclient.rest.response;

import java.util.List;

public record ExchangesResponse(List<ExchangeRecord> data, Long timestamp) {

}
