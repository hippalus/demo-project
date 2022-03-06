package org.example.demo.coincapclient.rest.request;

import java.util.Objects;

public record CandlesRetrieveRequest(String exchange,
                                     String interval,//m1, m5, m15, m30, h1, h2, h4, h8, h12, d1, w1
                                     String baseId,
                                     String quoteId,
                                     Long start,
                                     Long end) {

  public CandlesRetrieveRequest {
    Objects.requireNonNull(exchange, "Exchange must not be null!");
    Objects.requireNonNull(interval, "Interval must not be null!");
    Objects.requireNonNull(baseId, "BaseId must not be null!");
    Objects.requireNonNull(quoteId, "QuoteId must not be null!");
  }


  public static CandlesRetrieveRequest.CandlesRetrieveRequestBuilder builder() {
    return new CandlesRetrieveRequest.CandlesRetrieveRequestBuilder();
  }
  

  public static class CandlesRetrieveRequestBuilder {

    private String exchange;
    private String interval;
    private String baseId;
    private String quoteId;
    private Long start;
    private Long end;

    CandlesRetrieveRequestBuilder() {
    }

    public CandlesRetrieveRequest.CandlesRetrieveRequestBuilder exchange(final String exchange) {
      this.exchange = exchange;
      return this;
    }

    public CandlesRetrieveRequest.CandlesRetrieveRequestBuilder interval(final String interval) {
      this.interval = interval;
      return this;
    }

    public CandlesRetrieveRequest.CandlesRetrieveRequestBuilder baseId(final String baseId) {
      this.baseId = baseId;
      return this;
    }

    public CandlesRetrieveRequest.CandlesRetrieveRequestBuilder quoteId(final String quoteId) {
      this.quoteId = quoteId;
      return this;
    }

    public CandlesRetrieveRequest.CandlesRetrieveRequestBuilder start(final Long start) {
      this.start = start;
      return this;
    }

    public CandlesRetrieveRequest.CandlesRetrieveRequestBuilder end(final Long end) {
      this.end = end;
      return this;
    }

    public CandlesRetrieveRequest build() {
      return new CandlesRetrieveRequest(this.exchange, this.interval, this.baseId, this.quoteId, this.start, this.end);
    }

    public String toString() {
      return "CandlesRetrieveRequest.CandlesRetrieveRequestBuilder(exchange=" + this.exchange + ", interval=" + this.interval
          + ", baseId=" + this.baseId + ", quoteId=" + this.quoteId + ", start=" + this.start + ", end=" + this.end + ")";
    }
  }
}
