package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetHistoryResponse {

  private List<HistoryResponse> data;


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class HistoryResponse {

    private BigDecimal priceUsd;
    private Long time;

  }
}
