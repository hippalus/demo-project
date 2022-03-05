package org.example.demo.service.scheduled;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.coincapclient.CoinCap;
import org.example.demo.coincapclient.rest.request.AssetsRetrieveRequest;
import org.example.demo.coincapclient.rest.response.AssetResponse;
import org.example.demo.coincapclient.rest.response.AssetsResponse;
import org.example.demo.model.Asset;
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

  private Asset toModel(AssetResponse assetResponse) {
    return Asset.builder()
        .assetId(assetResponse.id())
        .changePercent24Hr(assetResponse.changePercent24Hr())
        .explorer(assetResponse.explorer())
        .marketCapUsd(assetResponse.marketCapUsd())
        .maxSupply(assetResponse.maxSupply())
        .name(assetResponse.name())
        .priceUsd(assetResponse.priceUsd())
        .supply(assetResponse.supply())
        .rank(assetResponse.rank())
        .volumeUsd24Hr(assetResponse.volumeUsd24Hr())
        .vwap24Hr(assetResponse.vwap24Hr())
        .symbol(assetResponse.symbol())
        .build();
  }
}
