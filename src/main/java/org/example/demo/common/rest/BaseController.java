package org.example.demo.common.rest;

public class BaseController {

  protected <T> Response<T> respond(T item) {
    return ResponseBuilder.build(item);
  }

  protected Response<ErrorResponse> respond(ErrorResponse errorResponse) {
    return ResponseBuilder.build(errorResponse);
  }
}
