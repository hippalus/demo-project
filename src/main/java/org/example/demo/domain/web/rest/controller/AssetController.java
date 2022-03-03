package org.example.demo.domain.web.rest.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.demo.domain.model.Asset;
import org.example.demo.domain.service.AssetService;
import org.example.demo.domain.web.rest.dto.res.AssetResponse;
import org.example.demo.domain.web.rest.dto.res.AssetsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/assets")
@RequiredArgsConstructor
public class AssetController {

  private final AssetService assetService;

  @GetMapping
  public ResponseEntity<AssetsResponse> retrieveLastNAssets(@RequestParam("size") @NotNull @Positive Integer size) {
    final List<Asset> assets = assetService.retrieveLastNAssets(size);
    final AssetsResponse assetsResponse = toResponse(assets);
    return ResponseEntity.ok(assetsResponse);
  }

  @DeleteMapping("/{name}")
  public ResponseEntity<Void> deleteByName(@PathVariable @NotNull @NotEmpty String name) {
    assetService.deleteByName(name);
    return ResponseEntity.noContent().build();
  }

  private AssetsResponse toResponse(final List<Asset> assets) {
    final List<AssetResponse> assetResponseList = assets.stream()
        .map(AssetResponse::fromModel)
        .collect(Collectors.toList());
    return new AssetsResponse(assetResponseList);
  }

}
