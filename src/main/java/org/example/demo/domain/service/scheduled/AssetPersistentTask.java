package org.example.demo.domain.service.scheduled;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.demo.coincapclient.CoinCap;
import org.example.demo.coincapclient.rest.request.AssetsRetrieveRequest;
import org.example.demo.coincapclient.rest.response.AssetResponse;
import org.example.demo.coincapclient.rest.response.AssetsResponse;
import org.example.demo.domain.model.Asset;
import org.example.demo.domain.service.AssetService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetPersistentTask {

  private final AssetService assetService;
  private final CoinCap coinCap;

  @Scheduled
  public void run(){
    final AssetsResponse response = coinCap.assets().retrieve(new AssetsRetrieveRequest());
    final List<Asset> assetList = response.getData()
        .stream()
        .map(this::toModel)
        .collect(Collectors.toList());
    assetService.save(assetList);
  }

  private Asset toModel(AssetResponse assetResponse) {
    return Asset.builder()
        .build();
  }
}
