package org.example.demo.domain.asset.repository.impl;

import com.google.common.collect.Iterables;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.domain.asset.model.Asset;
import org.example.demo.domain.asset.repository.CustomAssetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomAssetRepositoryJdbcTemplateImpl implements CustomAssetRepository {

  private static final String SQL_ASSET_INSERT = """
      INSERT INTO ASSET ("ASSET_ID", "RANK", "SYMBOL", "NAME", "SUPPLY", "MAX_SUPPLY", "MARKET_CAP_USD", "VOLUME_USD24_HR",
               "PRICE_USD", "CHANGE_PERCENT24_HR", "VWAP24_HR", "EXPLORER", "CREATED_AT", "UPDATED_AT")
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
           """;

  @Value("${jdbc.batch-insert-size}")
  private int batchInsertSize;

  private final JdbcTemplate jdbcTemplate;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void batchInsert(List<Asset> assetList) {
    Iterable<List<Asset>> partitions = Iterables.partition(assetList, batchInsertSize);
    partitions.forEach(this::save);
  }

  private void save(List<Asset> assets) {
    jdbcTemplate.batchUpdate(SQL_ASSET_INSERT, new AssetBatchPreparedStatementSetter(assets));
  }

  private record AssetBatchPreparedStatementSetter(List<Asset> assets) implements BatchPreparedStatementSetter {

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
      Asset asset = assets.get(i);
      ps.setString(1, asset.getAssetId());
      ps.setString(2, asset.getRank());
      ps.setString(3, asset.getSymbol());
      ps.setString(4, asset.getName());
      ps.setBigDecimal(5, asset.getSupply());
      ps.setBigDecimal(6, asset.getMaxSupply());
      ps.setBigDecimal(7, asset.getMarketCapUsd());
      ps.setBigDecimal(8, asset.getVolumeUsd24Hr());
      ps.setBigDecimal(9, asset.getPriceUsd());
      ps.setBigDecimal(10, asset.getChangePercent24Hr());
      ps.setBigDecimal(11, asset.getVwap24Hr());
      ps.setString(12, asset.getExplorer());
      ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
      ps.setTimestamp(14, null);
    }

    @Override
    public int getBatchSize() {
      return assets.size();
    }
  }
}
