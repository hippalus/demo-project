package org.example.demo.service;

import java.util.List;
import org.example.demo.model.Asset;

public interface AssetService {

  List<Asset> retrieveLastNAssets(Integer size);

  void deleteByName(String name);

  void save(List<Asset> assetList);

  Asset save(Asset assetList);
}
