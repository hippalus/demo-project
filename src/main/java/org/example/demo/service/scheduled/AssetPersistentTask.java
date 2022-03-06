package org.example.demo.service.scheduled;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.coincapclient.CoinCap;
import org.example.demo.coincapclient.rest.request.AssetsRetrieveRequest;
import org.example.demo.coincapclient.rest.response.Asset;
import org.example.demo.coincapclient.rest.response.AssetsResponse;
import org.example.demo.service.AssetService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class AssetPersistentTask {

  private static final int LIMIT = 100;

  private final AssetService assetService;
  private final CoinCap coinCap;

  @Scheduled(fixedRateString = "${app.scheduled.fix-rate}")
  public void run() {
    int offset = 1;
    while (true) {
      final AssetsRetrieveRequest request = prepareRequest(offset);
      final AssetsResponse response = coinCap.assets().retrieve(request);
      if (response.data().isEmpty()) {
        break;
      }
      final List<org.example.demo.model.Asset> assetList = toModel(response);
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

  private List<org.example.demo.model.Asset> toModel(AssetsResponse response) {
    return response.data()
        .stream()
        .map(this::toModel)
        .toList();
  }

  private org.example.demo.model.Asset toModel(Asset asset) {
    return org.example.demo.model.Asset.builder()
        .assetId(asset.id())
        .changePercent24Hr(asset.changePercent24Hr())
        .explorer(asset.explorer())
        .marketCapUsd(asset.marketCapUsd())
        .maxSupply(asset.maxSupply())
        .name(asset.name())
        .priceUsd(asset.priceUsd())
        .supply(asset.supply())
        .rank(asset.rank())
        .volumeUsd24Hr(asset.volumeUsd24Hr())
        .vwap24Hr(asset.vwap24Hr())
        .symbol(asset.symbol())
        .build();
  }
}
