package org.example.demo.coincapclient.rest.request;

import lombok.Builder;

@Builder
public record MarketsRetrieveRequest(String exchangeId,
                                     String baseSymbol,
                                     String quoteSymbol,
                                     String baseId,
                                     String quoteId,
                                     String assetSymbol,
                                     String assetId,
                                     Integer limit,
                                     Integer offset) {


  public static MarketsRetrieveRequest.MarketsRetrieveRequestBuilder builder() {
    return new MarketsRetrieveRequest.MarketsRetrieveRequestBuilder();
  }

  public static class MarketsRetrieveRequestBuilder {

    private String exchangeId;
    private String baseSymbol;
    private String quoteSymbol;
    private String baseId;
    private String quoteId;
    private String assetSymbol;
    private String assetId;
    private Integer limit;
    private Integer offset;

    MarketsRetrieveRequestBuilder() {
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder exchangeId(final String exchangeId) {
      this.exchangeId = exchangeId;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder baseSymbol(final String baseSymbol) {
      this.baseSymbol = baseSymbol;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder quoteSymbol(final String quoteSymbol) {
      this.quoteSymbol = quoteSymbol;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder baseId(final String baseId) {
      this.baseId = baseId;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder quoteId(final String quoteId) {
      this.quoteId = quoteId;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder assetSymbol(final String assetSymbol) {
      this.assetSymbol = assetSymbol;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder assetId(final String assetId) {
      this.assetId = assetId;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder limit(final Integer limit) {
      this.limit = limit;
      return this;
    }

    public MarketsRetrieveRequest.MarketsRetrieveRequestBuilder offset(final Integer offset) {
      this.offset = offset;
      return this;
    }

    public MarketsRetrieveRequest build() {
      return new MarketsRetrieveRequest(this.exchangeId, this.baseSymbol, this.quoteSymbol, this.baseId, this.quoteId,
          this.assetSymbol, this.assetId, this.limit, this.offset);
    }

    public String toString() {
      return "MarketsRetrieveRequest.MarketsRetrieveRequestBuilder(exchangeId=" + this.exchangeId + ", baseSymbol="
          + this.baseSymbol + ", quoteSymbol=" + this.quoteSymbol + ", baseId=" + this.baseId + ", quoteId=" + this.quoteId
          + ", assetSymbol=" + this.assetSymbol + ", assetId=" + this.assetId + ", limit=" + this.limit + ", offset="
          + this.offset + ")";
    }
  }
}
