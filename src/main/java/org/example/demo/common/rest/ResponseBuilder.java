package org.example.demo.common.rest;

public class ResponseBuilder {

  private ResponseBuilder() {
  }

  public static <T> Response<T> build(T item) {
    return new Response<>(item);
  }

  public static Response<ErrorResponse> build(ErrorResponse errorResponse) {
    return new Response<>(errorResponse);
  }
}
