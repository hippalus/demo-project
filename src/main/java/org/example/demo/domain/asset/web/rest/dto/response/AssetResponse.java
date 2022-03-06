package org.example.demo.domain.asset.web.rest.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.example.demo.domain.asset.model.Asset;

public record AssetResponse(Long id,
                            String assetId,
                            String rank,
                            String symbol,
                            String name,
                            BigDecimal supply,
                            BigDecimal maxSupply,
                            BigDecimal marketCapUsd,
                            BigDecimal volumeUsd24Hr,
                            BigDecimal priceUsd,
                            BigDecimal changePercent24Hr,
                            BigDecimal vwap24Hr,
                            String explorer,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt) {



  public static AssetResponse fromModel(final Asset asset) {
    return AssetResponse.builder()
        .id(asset.getId())
        .assetId(asset.getAssetId())
        .changePercent24Hr(asset.getChangePercent24Hr())
        .explorer(asset.getExplorer())
        .marketCapUsd(asset.getMarketCapUsd())
        .maxSupply(asset.getMaxSupply())
        .name(asset.getName())
        .priceUsd(asset.getPriceUsd())
        .supply(asset.getSupply())
        .rank(asset.getRank())
        .volumeUsd24Hr(asset.getVolumeUsd24Hr())
        .vwap24Hr(asset.getVwap24Hr())
        .symbol(asset.getSymbol())
        .createdAt(asset.getCreatedAt())
        .updatedAt(asset.getUpdatedAt())
        .build();
  }



  public static List<AssetResponse> fromModel(final List<Asset> assets) {
    return assets.stream()
        .map(AssetResponse::fromModel)
        .toList();
  }


  public static AssetResponseBuilder builder() {
    return new AssetResponseBuilder();
  }
  public static class AssetResponseBuilder {
    private Long id;
    private String assetId;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    AssetResponseBuilder() {
    }

    public AssetResponse.AssetResponseBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public AssetResponse.AssetResponseBuilder assetId(final String assetId) {
      this.assetId = assetId;
      return this;
    }

    public AssetResponse.AssetResponseBuilder rank(final String rank) {
      this.rank = rank;
      return this;
    }

    public AssetResponse.AssetResponseBuilder symbol(final String symbol) {
      this.symbol = symbol;
      return this;
    }

    public AssetResponse.AssetResponseBuilder name(final String name) {
      this.name = name;
      return this;
    }

    public AssetResponse.AssetResponseBuilder supply(final BigDecimal supply) {
      this.supply = supply;
      return this;
    }

    public AssetResponse.AssetResponseBuilder maxSupply(final BigDecimal maxSupply) {
      this.maxSupply = maxSupply;
      return this;
    }

    public AssetResponse.AssetResponseBuilder marketCapUsd(final BigDecimal marketCapUsd) {
      this.marketCapUsd = marketCapUsd;
      return this;
    }

    public AssetResponse.AssetResponseBuilder volumeUsd24Hr(final BigDecimal volumeUsd24Hr) {
      this.volumeUsd24Hr = volumeUsd24Hr;
      return this;
    }

    public AssetResponse.AssetResponseBuilder priceUsd(final BigDecimal priceUsd) {
      this.priceUsd = priceUsd;
      return this;
    }

    public AssetResponse.AssetResponseBuilder changePercent24Hr(final BigDecimal changePercent24Hr) {
      this.changePercent24Hr = changePercent24Hr;
      return this;
    }

    public AssetResponse.AssetResponseBuilder vwap24Hr(final BigDecimal vwap24Hr) {
      this.vwap24Hr = vwap24Hr;
      return this;
    }

    public AssetResponse.AssetResponseBuilder explorer(final String explorer) {
      this.explorer = explorer;
      return this;
    }

    public AssetResponse.AssetResponseBuilder createdAt(final LocalDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public AssetResponse.AssetResponseBuilder updatedAt(final LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public AssetResponse build() {
      return new AssetResponse(this.id, this.assetId, this.rank, this.symbol, this.name, this.supply, this.maxSupply, this.marketCapUsd, this.volumeUsd24Hr, this.priceUsd, this.changePercent24Hr, this.vwap24Hr, this.explorer, this.createdAt, this.updatedAt);
    }

    public String toString() {
      return "AssetResponse.AssetResponseBuilder(id=" + this.id + ", assetId=" + this.assetId + ", rank=" + this.rank + ", symbol=" + this.symbol + ", name=" + this.name + ", supply=" + this.supply + ", maxSupply=" + this.maxSupply + ", marketCapUsd=" + this.marketCapUsd + ", volumeUsd24Hr=" + this.volumeUsd24Hr + ", priceUsd=" + this.priceUsd + ", changePercent24Hr=" + this.changePercent24Hr + ", vwap24Hr=" + this.vwap24Hr + ", explorer=" + this.explorer + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
    }
  }
}

