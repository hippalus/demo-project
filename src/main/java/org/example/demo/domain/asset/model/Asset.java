package org.example.demo.domain.asset.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

@Getter
@AllArgsConstructor
@Builder
public class Asset implements Persistable<Long> {

  @Id
  private final Long id;
  private final String assetId;
  private final String rank;
  private final String symbol;
  private final String name;
  private final BigDecimal supply;
  private final BigDecimal maxSupply;
  private final BigDecimal marketCapUsd;
  private final BigDecimal volumeUsd24Hr;
  private final BigDecimal priceUsd;
  private final BigDecimal changePercent24Hr;
  private final BigDecimal vwap24Hr;
  private final String explorer;
  @CreatedDate
  private final LocalDateTime createdAt;
  @LastModifiedDate
  private final LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Asset asset = (Asset) o;
    return Objects.equals(getId(), asset.getId()) && Objects.equals(getRank(), asset.getRank())
        && Objects.equals(getSymbol(), asset.getSymbol()) && Objects.equals(getName(), asset.getName())
        && Objects.equals(getSupply(), asset.getSupply()) && Objects.equals(getMaxSupply(),
        asset.getMaxSupply()) && Objects.equals(getMarketCapUsd(), asset.getMarketCapUsd()) && Objects.equals(
        getVolumeUsd24Hr(), asset.getVolumeUsd24Hr()) && Objects.equals(getPriceUsd(), asset.getPriceUsd())
        && Objects.equals(getChangePercent24Hr(), asset.getChangePercent24Hr()) && Objects.equals(getVwap24Hr(),
        asset.getVwap24Hr()) && Objects.equals(getExplorer(), asset.getExplorer()) && Objects.equals(
        getCreatedAt(), asset.getCreatedAt()) && Objects.equals(getUpdatedAt(), asset.getUpdatedAt());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getRank(), getSymbol(), getName(), getSupply(), getMaxSupply(), getMarketCapUsd(),
        getVolumeUsd24Hr(), getPriceUsd(), getChangePercent24Hr(), getVwap24Hr(), getExplorer(), getCreatedAt(), getUpdatedAt());
  }

  @Override
  public boolean isNew() {
    return id == null;
  }
}
