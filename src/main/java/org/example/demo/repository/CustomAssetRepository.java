package org.example.demo.repository;

import java.util.List;
import org.example.demo.model.Asset;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomAssetRepository {

  void saveAllBatch(List<Asset> assetList);

}
