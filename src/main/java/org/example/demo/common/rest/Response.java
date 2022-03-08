package org.example.demo.common.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

  private T data;
  private ErrorResponse errors;
  private Long timestamp;

  public Response(ErrorResponse errors) {
    this.errors = errors;
  }

  public Response(T data) {
    this.data = data;
  }

  public ErrorResponse errors() {
    return errors;
  }

  public T data() {
    return data;
  }

  public Long timestamp() {
    return timestamp;
  }

}
