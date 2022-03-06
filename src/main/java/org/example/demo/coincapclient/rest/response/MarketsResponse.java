package org.example.demo.coincapclient.rest.response;

import java.util.List;

public record MarketsResponse(List<Market> data, Long timestamp) {

}
