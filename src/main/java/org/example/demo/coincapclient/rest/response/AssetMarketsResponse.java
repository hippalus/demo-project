package org.example.demo.coincapclient.rest.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetMarketsResponse {

  private List<Market> data;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Market {

    private String exchangeId;
    private String baseId;
    private String quoteId;
    private String baseSymbol;
    private String quoteSymbol;
    private BigDecimal volumeUsd24Hr;
    private BigDecimal priceUsd;
    private BigDecimal volumePercent;
  }

}
