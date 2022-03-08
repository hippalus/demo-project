package org.example.demo.domain.asset.service.scheduled;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.coincapclient.CoinCap;
import org.example.demo.coincapclient.rest.request.AssetsRetrieveRequest;
import org.example.demo.coincapclient.rest.response.AssetRecord;
import org.example.demo.coincapclient.rest.response.AssetsResponse;
import org.example.demo.domain.asset.model.Asset;
import org.example.demo.domain.asset.service.AssetService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.scheduled.asset.enabled", havingValue = "true")
public class AssetPersistentTask {

  private static final int LIMIT = 100;

  private final AssetService assetService;
  private final CoinCap coinCap;

  @Scheduled(fixedRateString = "${app.scheduled.fix-rate}")
  public void run() {
    int offset = 1;
    while (true) {
      final AssetsRetrieveRequest request = prepareRequest(offset);
      final var response = coinCap.assets().retrieve(request);
      if (response.data().isEmpty()) {
        break;
      }
      final List<Asset> assetList = toModel(response);
      assetService.save(assetList);
      offset += LIMIT;
    }
  }

  private AssetsRetrieveRequest prepareRequest(int offset) {
    return AssetsRetrieveRequest.builder()
        .offset(offset)
        .limit(LIMIT)
        .build();
  }

  private List<Asset> toModel(AssetsResponse response) {
    return response.data()
        .stream()
        .map(this::toModel)
        .toList();
  }

  private Asset toModel(AssetRecord assetRecord) {
    return Asset.builder()
        .assetId(assetRecord.id())
        .changePercent24Hr(assetRecord.changePercent24Hr())
        .explorer(assetRecord.explorer())
        .marketCapUsd(assetRecord.marketCapUsd())
        .maxSupply(assetRecord.maxSupply())
        .name(assetRecord.name())
        .priceUsd(assetRecord.priceUsd())
        .supply(assetRecord.supply())
        .rank(assetRecord.rank())
        .volumeUsd24Hr(assetRecord.volumeUsd24Hr())
        .vwap24Hr(assetRecord.vwap24Hr())
        .symbol(assetRecord.symbol())
        .build();
  }
}
