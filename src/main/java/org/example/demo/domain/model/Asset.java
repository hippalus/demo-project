package org.example.demo.domain.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Asset {

  @Id
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
}
