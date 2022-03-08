package org.example.demo.domain.asset.web.rest.controller;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.demo.common.rest.BaseController;
import org.example.demo.common.rest.Response;
import org.example.demo.domain.asset.model.Asset;
import org.example.demo.domain.asset.service.AssetService;
import org.example.demo.domain.asset.web.rest.dto.response.AssetResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/assets")
@RequiredArgsConstructor
public class AssetController extends BaseController {

  private final AssetService assetService;

  @GetMapping
  public Response<List<AssetResponse>> retrieveLastNAssets(@RequestParam("lastN") @NotNull @Positive Integer size) {
    final List<Asset> assets = assetService.retrieveLastNAssets(size);
    return respond(AssetResponse.fromModel(assets));
  }

  @DeleteMapping("/{name}")
  public Response<Void> deleteByName(@PathVariable @NotNull @NotEmpty String name) {
    assetService.deleteByName(name);
    return respond((Void) null);
  }

}
