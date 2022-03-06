package org.example.demo.coincapclient.rest.response;

import java.util.List;

public record RatesResponse(List<Rate> data, Long timestamp) {

}
