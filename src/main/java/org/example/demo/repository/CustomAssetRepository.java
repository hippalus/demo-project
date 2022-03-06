package org.example.demo.repository;

import java.util.List;
import org.example.demo.model.Asset;

public interface CustomAssetRepository {

  void saveAllBatch(List<Asset> assetList);

}
