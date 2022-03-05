package org.example.demo.coincapclient.rest.request;

import java.util.Set;

public record AssetsRetrieveRequest(String search, Set<String> ids, Integer limit, Integer offset) {

  public static AssetsRetrieveRequest.AssetsRetrieveRequestBuilder builder() {
    return new AssetsRetrieveRequest.AssetsRetrieveRequestBuilder();
  }

  public static class AssetsRetrieveRequestBuilder {

    private String search;
    private Set<String> ids;
    private Integer limit;
    private Integer offset;

    AssetsRetrieveRequestBuilder() {
    }

    public AssetsRetrieveRequest.AssetsRetrieveRequestBuilder search(final String search) {
      this.search = search;
      return this;
    }

    public AssetsRetrieveRequest.AssetsRetrieveRequestBuilder ids(final Set<String> ids) {
      this.ids = ids;
      return this;
    }

    public AssetsRetrieveRequest.AssetsRetrieveRequestBuilder limit(final Integer limit) {
      this.limit = limit;
      return this;
    }

    public AssetsRetrieveRequest.AssetsRetrieveRequestBuilder offset(final Integer offset) {
      this.offset = offset;
      return this;
    }

    public AssetsRetrieveRequest build() {
      return new AssetsRetrieveRequest(this.search, this.ids, this.limit, this.offset);
    }

    public String toString() {
      return "AssetsRetrieveRequest.AssetsRetrieveRequestBuilder(search=" + this.search + ", ids=" + this.ids + ", limit="
          + this.limit + ", offset=" + this.offset + ")";
    }
  }

}
