package org.example.demo.coincapclient.rest.response;

import java.util.List;

public record CandlesResponse(List<Candle> data,Long timestamp) {

}
