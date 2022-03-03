package org.example.demo.domain.service;

import java.util.List;
import org.example.demo.domain.model.Asset;

public interface AssetService {

  List<Asset> retrieveLastNAssets(Integer size);

  void deleteByName(String name);

  void save(List<Asset> assetList);

  void save(Asset assetList);
}
