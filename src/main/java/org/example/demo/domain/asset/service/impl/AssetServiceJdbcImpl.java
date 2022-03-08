package org.example.demo.domain.asset.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.common.exception.DataNotFoundException;
import org.example.demo.domain.asset.model.Asset;
import org.example.demo.domain.asset.repository.AssetJdbcRepository;
import org.example.demo.domain.asset.repository.CustomAssetRepository;
import org.example.demo.domain.asset.service.AssetService;
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
      throw new DataNotFoundException("asset.data.not.found",name);
    }
    assetJdbcRepository.deleteByName(name);
  }

  @Override
  public void save(List<Asset> assetList) {
    customAssetRepository.batchInsert(assetList);
  }

  @Override
  public Asset save(Asset asset) {
    return assetJdbcRepository.save(asset);
  }
}
