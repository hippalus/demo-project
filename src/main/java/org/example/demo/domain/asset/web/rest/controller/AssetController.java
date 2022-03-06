package org.example.demo.domain.asset.web.rest.controller;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.demo.domain.asset.model.Asset;
import org.example.demo.domain.asset.service.AssetService;
import org.example.demo.domain.asset.web.rest.dto.response.AssetResponse;
import org.example.demo.domain.asset.web.rest.dto.response.AssetsResponse;
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
  public ResponseEntity<AssetsResponse> retrieveLastNAssets(@RequestParam("lastN") @NotNull @Positive Integer size) {
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
    List<AssetResponse> assetResponseList = AssetResponse.fromModel(assets);
    return new AssetsResponse(assetResponseList);
  }

}
