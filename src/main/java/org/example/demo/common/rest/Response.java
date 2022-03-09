package org.example.demo.common.rest;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
  }

  public Response(T data) {
    this.data = data;
    this.timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
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
