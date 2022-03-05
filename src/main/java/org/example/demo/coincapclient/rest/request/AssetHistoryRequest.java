package org.example.demo.coincapclient.rest.request;

// interval m1, m5, m15, m30, h1, h2, h6, h12, d1
public record AssetHistoryRequest(String interval, Long start, Long end) {

  public AssetHistoryRequest(String interval) {
    this(interval, null, null);
  }
}
