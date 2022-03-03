package org.example.demo.domain.web.rest.dto.res;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo.domain.model.Asset;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetResponse {

  private String id;
  private String rank;
  private String symbol;
  private String name;
  private BigDecimal supply;
  private BigDecimal maxSupply;
  private BigDecimal marketCapUsd;
  private BigDecimal volumeUsd24Hr;
  private BigDecimal priceUsd;
  private BigDecimal changePercent24Hr;
  private BigDecimal vwap24Hr;
  private String explorer;

  public static AssetResponse fromModel(final Asset asset) {
    return null;
  }
}

