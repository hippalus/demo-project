package org.example.demo.domain.asset.repository;

import java.util.List;
import org.example.demo.domain.asset.model.Asset;

public interface CustomAssetRepository {

  void savaBatch(List<Asset> assetList);

}
