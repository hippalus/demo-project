package org.example.demo.domain.asset.service;

import java.util.List;
import org.example.demo.domain.asset.model.Asset;

public interface AssetService {

  List<Asset> retrieveLastNAssets(Integer size);

  void deleteByName(String name);

  void save(List<Asset> assetList);

  Asset save(Asset assetList);
}
