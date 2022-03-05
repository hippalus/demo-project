package org.example.demo.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.exception.DataNotFoundException;
import org.example.demo.model.Asset;
import org.example.demo.repository.AssetJdbcRepository;
import org.example.demo.repository.CustomAssetRepository;
import org.example.demo.service.AssetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetServiceJdbcImpl implements AssetService {

  private final AssetJdbcRepository assetJdbcRepository;
  private final CustomAssetRepository customAssetRepository;

  @Override
  public List<Asset> retrieveLastNAssets(Integer size) {
    return assetJdbcRepository.findLastN(size);
  }

  @Override
  public void deleteByName(String name) {
    if (!assetJdbcRepository.existsByName(name)) {
      throw new DataNotFoundException(name+ " could not be found!");
    }
    assetJdbcRepository.deleteByName(name);
  }

  @Override
  public void save(List<Asset> assetList) {
    customAssetRepository.saveAllBatch(assetList);
  }

  @Override
  public Asset save(Asset asset) {
    return assetJdbcRepository.save(asset);
  }
}
