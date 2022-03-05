package org.example.demo.coincapclient.rest.request;

public record AssetMarketsRequest(Integer limit, Integer offset) {

  public AssetMarketsRequest() {
    this(null, null);
  }
}
